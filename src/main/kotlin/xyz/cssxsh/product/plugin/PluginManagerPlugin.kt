package xyz.cssxsh.product.plugin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.extension.*
import net.mamoe.mirai.console.plugin.*
import net.mamoe.mirai.console.plugin.jvm.*
import xyz.cssxsh.product.plugin.command.*
import xyz.cssxsh.product.plugin.data.*

public object PluginManagerPlugin : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.cssxsh.project.plugin-manager",
        name = "plugin-manager",
        version = "1.0.0",
    ) {
        author("cssxsh")
    }
) {

    private val manager = MiraiConsole.pluginManager

    override fun PluginComponentStorage.onLoad() {
        runAfterStartup {
            launch {
                for (id in PluginManagerData.plugins) {
                    val target = manager.plugins.find { it.id == id || it.name == id } ?: continue
                    try {
                        manager.disablePlugin(plugin = target)
                        (target as CoroutineScope).cancel("cancel by plugin-manager")
                    } catch (cause: Exception) {
                        logger.warning("${target.id} 终止异常", cause)
                    }
                    logger.info("目标插件 ${target.id} 处理完毕")
                }
            }
        }
    }

    override fun onEnable() {
        PluginManagerData.reload()
        PluginManagerCommand.register()
    }

    override fun onDisable() {
        PluginManagerCommand.unregister()
    }
}