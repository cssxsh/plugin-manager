# Plugin Manager

> 用于 Mirai Console 的 插件 禁用/启用 管理器 

[![Release](https://img.shields.io/github/v/release/cssxsh/plugin-manager)](https://github.com/cssxsh/plugin-manager/releases)
![Downloads](https://img.shields.io/github/downloads/cssxsh/plugin-manager/total)
[![maven-central](https://img.shields.io/maven-central/v/xyz.cssxsh.mirai/plugin-manager)](https://search.maven.org/artifact/xyz.cssxsh.mirai/plugin-manager)

**使用前应该查阅的相关文档或项目**

* [User Manual](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md)
* [Permission Command](https://github.com/mamoe/mirai/blob/dev/mirai-console/docs/BuiltInCommands.md#permissioncommand)
* [Chat Command](https://github.com/project-mirai/chat-command)

本插件使用 Mirai Console 的生命周期相关API进行插件管理

## 指令

* `/plugin disable <id>`  
  例如 `/plugin disable "Chat Command"` (用插件名停止插件, 插件名可以从 `/status` 的返回内容知晓)  
  例如 `/plugin disable xyz.cssxsh.mirai.plugin.novelai-helper` (用插件ID停止插件, 可以从 `data`, `config` 文件夹知晓)  
* `/plugin enable <id>`  
  其 `<id>` 和 disable 一致，用于取消插件禁用