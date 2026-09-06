# Deadly Monsters — Fabric 26.2 移植版

这是 [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters) 从 Minecraft Forge 1.12.2 原生移植到 Minecraft 26.2 / Fabric 的版本

本分支尽量保留原版 `dmonsters` 注册 ID、玩法身份、资源和行为，同时用当前 Minecraft 与 Fabric API 机制替代已经移除的旧 Forge API

## 目标环境

- Minecraft **26.2**
- Fabric Loader **0.19.5**
- Fabric API **0.159.0+26.2**
- Fabric Loom **1.17.20**
- Gradle **9.5.1**
- Java **25**
- 模组 ID `dmonsters`

## 当前状态

Fabric 平台层源码迁移已经完成

当前分支已经替换构建系统、模组入口、注册系统、配置、事件、自然生成与客户端注册入口

完整构建与实际游玩验证仍按 [`测试清单.md`](测试清单.md) 执行，在完成手动构建前不把本分支标记为已发布验证

已迁移内容包括

- 12 种原版怪物及对应实体类型、属性、行为和渲染
- 12 种原版怪物刷怪蛋
- 原版方块和特殊行为
- 强化钢筋、四种鱼叉、幸运蛋、达贡与原版怪物掉落功能物品
- 幸运蛋与达贡投射物
- 原版贴图与声音
- 当前格式的方块状态文件、方块模型、物品模型与物品定义
- 17 个原版合成配方
- 当前格式的实体与方块战利品表
- 怪物生命、攻击、速度、自然生成权重和禁用开关配置
- 突变史蒂夫、腹中胎儿、闹鬼牛、异形水鬼专用配置
- 通过 Fabric API 生物群系修改机制实现的自然生成

更详细的移植决策见 [`移植说明.md`](移植说明.md)

## 平台实现

本分支没有引入 Architectury 或其他跨加载器框架

玩法类、实体行为、模型、渲染器、配方、战利品表与大部分资源继续复用 Minecraft 原生实现

平台边界使用 Fabric 原生接口完成

- `ModInitializer` 与 `ClientModInitializer` 负责服务端通用入口和客户端入口
- 方块、物品、实体、声音与创造模式标签页使用 Minecraft 原生注册表
- 实体默认属性使用 Fabric API 属性注册
- 自然生成使用 Fabric API 生物群系修改接口
- 实体载入和玩家攻击行为使用 Fabric 事件
- 模型层使用 Fabric API 注册，实体渲染器使用当前 Minecraft 原生注册入口
- 配置使用轻量 JSON 文件，不依赖额外配置模组

## 配方查看与信息显示兼容性

本模组没有自定义配方类型、配方容器或特殊工作台，现有 17 个合成配方全部使用 Minecraft 原生配方体系

JEI 与 REI 的 Fabric 26.2 版本可以直接理解这类标准注册与标准配方数据，因此当前判断为结构兼容，不增加 Deadly Monsters 专用桥接层

Jade 的 Fabric 26.2 版本可以通过标准方块和实体注册信息显示基础内容，当前没有需要专用信息提供器才能读取的机器状态、库存、流体或配方进度

结构兼容不等于已经完成联合实机验证，发布前仍需按 [`测试清单.md`](测试清单.md) 分别验证单人世界与专用服务器

完整兼容性说明见 [`兼容性.md`](兼容性.md)

## 开发运行

本仓库不包含 Gradle Wrapper

Fabric 26.2 开发环境使用 Gradle 9.5.1 与 JDK 25

```bash
gradle runClient
gradle runServer
gradle build
```

GitHub Actions 默认只保留手动构建入口，不会在每次推送时自动触发

## 配置

Fabric 首次运行后会生成 `config/dmonsters.json`

配置字段继续保留原版移植版的含义和默认值

`spawnRate` 与 `disabled` 会影响生物群系生成列表，修改后需要重启世界或专用服务器

Fabric 版没有为了配置额外依赖 Cloth Config、YACL 或其他配置框架

## 移植原则

这是原生 Fabric 移植，不是 NeoForge API 模拟层，也不是旧版兼容垫片

少量 1.12.2 实现细节无法或不应该逐字复制

- 不再接入已经过时的 Hostile Worlds Invasions 旧版联动
- 旧版数字 `dayLengthTicks` 已退役，日光降临与闹鬼牛使用 Minecraft 26.2 当前世界时钟标记
- 异形水鬼原版深水搜索存在近似每游戏刻大范围扫描问题，移植版保留可见行为并限制刷新频率
- 旧元数据物品映射到当前独立物品 ID
- 方块修改类物品使用当前交互权限检查，避免绕过现代保护逻辑
- 不为了双平台形式统一去大规模重构现有玩法代码

## 上游与许可

原项目 [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters)

上游使用 MIT 许可证，本仓库保留原始版权与许可文本 [`LICENSE`](LICENSE)
