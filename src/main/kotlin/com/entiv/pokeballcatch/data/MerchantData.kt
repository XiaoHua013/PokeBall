package com.entiv.pokeballcatch.data

import com.entiv.core.common.submit
import com.entiv.core.debug.debug
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.inventory.Merchant
import org.bukkit.inventory.MerchantRecipe

object MerchantData : DataWrapper<Merchant>(Merchant::class) {
    override fun entityWriteToNbt(entity: Merchant, compound: NBTCompound) {
        val compoundList = compound.getCompoundList("recipes")

        entity.recipes.forEach { recipe ->
            val recipeNBT = compoundList.addCompound()

            recipeNBT.setItemStack("result", recipe.result)
            recipeNBT.setInteger("uses", recipe.uses)
            recipeNBT.setInteger("maxUses", recipe.maxUses)
            recipeNBT.setBoolean("hasExperienceReward", recipe.hasExperienceReward())
            recipeNBT.setInteger("villagerExperience", recipe.villagerExperience)
            recipeNBT.setFloat("priceMultiplier", recipe.priceMultiplier)
            recipeNBT.setInteger("demand", recipe.demand)
            recipeNBT.setInteger("specialPrice", recipe.specialPrice)

            val ingredientsNBT = recipeNBT.getCompoundList("ingredients")

            recipe.ingredients.forEach {
                ingredientsNBT.addCompound().setItemStack("item", it)
            }
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Merchant) {
        val compoundList = compound.getCompoundList("recipes")

        val recipes = mutableListOf<MerchantRecipe>()

        compoundList.forEachIndexed { index, recipe ->
            val itemStack = recipe.getItemStack("result")
            val uses = recipe.getInteger("uses")
            val maxUses = recipe.getInteger("maxUses")
            val hasExperienceReward = recipe.getBoolean("hasExperienceReward")
            val villagerExperience = recipe.getInteger("villagerExperience")
            val priceMultiplier = recipe.getFloat("priceMultiplier")
            val demand = recipe.getInteger("demand")
            val specialPrice = recipe.getInteger("specialPrice")

            val ingredients = recipe.getCompoundList("ingredients").map { it.getItemStack("item") }

            MerchantRecipe(itemStack, uses, maxUses, hasExperienceReward, villagerExperience, priceMultiplier, demand, specialPrice).let {
                it.ingredients = ingredients
                recipes.add(it)
            }
        }

        recipes.forEach {
            debug("读取交易记录: recipe = ${it.ingredients}")
        }

        submit {
            entity.recipes = recipes
        }
    }

    override fun entityWriteToComponent(entity: Merchant, components: MutableList<Component>) {

    }
}