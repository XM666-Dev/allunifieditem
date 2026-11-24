package com.xm666.allunifieditem.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.xm666.allunifieditem.handler.DamageCleanerHandler;
import com.xm666.allunifieditem.handler.NameConverterHandler;
import net.minecraft.core.component.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ComponentUnifierMixin {
    @Mixin(ItemStack.class)
    private static abstract class ItemStackMixin {
        @Inject(method = "<init>(Lnet/minecraft/world/level/ItemLike;ILnet/minecraft/core/component/PatchedDataComponentMap;)V", at = @At("HEAD"))
        private static void onInit(ItemLike item, int count, PatchedDataComponentMap components, CallbackInfo ci) {
            DamageCleanerHandler.clean(components);
            NameConverterHandler.convert(components);
        }

        @WrapOperation(method = "set", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/component/PatchedDataComponentMap;set(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
        private <T> T wrapSet(PatchedDataComponentMap instance, DataComponentType<? super T> component, T value, Operation<T> original) {
            if (component == DataComponents.DAMAGE) {
                if ((Integer) value == 0) {
                    return (T) instance.remove(DataComponents.DAMAGE);
                }
            } else if (component == DataComponents.CUSTOM_NAME) {
                return original.call(instance, DataComponents.ITEM_NAME, NameConverterHandler.trySimplify((Component) value));
            }
            return original.call(instance, component, value);
        }

        @Inject(method = "applyComponentsAndValidate", at = @At("TAIL"))
        private void onApplyComponentsAndValidate(DataComponentPatch patch, CallbackInfo ci) {
            var stack = (ItemStack) (Object) this;
            DamageCleanerHandler.clean(stack);
            NameConverterHandler.convert(stack);
        }

        @Inject(method = "applyComponents(Lnet/minecraft/core/component/DataComponentPatch;)V", at = @At("TAIL"))
        private void onApplyComponents(DataComponentPatch patch, CallbackInfo ci) {
            var stack = (ItemStack) (Object) this;
            DamageCleanerHandler.clean(stack);
            NameConverterHandler.convert(stack);
        }

        @Inject(method = "applyComponents(Lnet/minecraft/core/component/DataComponentMap;)V", at = @At("TAIL"))
        private void onApplyComponents(DataComponentMap components, CallbackInfo ci) {
            var stack = (ItemStack) (Object) this;
            DamageCleanerHandler.clean(stack);
            NameConverterHandler.convert(stack);
        }
    }
}
