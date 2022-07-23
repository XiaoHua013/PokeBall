package com.entiv.pokeballcatch.pokeball

import org.bukkit.entity.Entity
import kotlin.reflect.KClass

class PokeBallCatchState(
    val clazz: KClass<out Entity>,
    val flag: Boolean
) {
    companion object {
        fun create(string: String): PokeBallCatchState {
            try {
                val split = string.split(":")

                @Suppress("UNCHECKED_CAST")
                val clazz = Class.forName("org.bukkit.entity.${split[0]}").kotlin as KClass<out Entity>
                val flag = split[1].toBoolean()

                return PokeBallCatchState(clazz, flag)
            } catch (e: Exception) {
                throw IllegalArgumentException("生物捕捉配置错误，请检查 $string")
            }
        }
    }
}