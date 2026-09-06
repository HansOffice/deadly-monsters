# Deadly Monsters — NeoForge 26.2 移植版

这是 [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters) 从 Minecraft Forge 1.12.2 原生移植到 Minecraft 26.2 / NeoForge 的版本

移植尽量保留原版 `dmonsters` 注册 ID、玩法身份、资源和行为，同时用当前 Minecraft / NeoForge 机制替代已经移除的旧 Forge API

## 目标环境

- Minecraft **26.2**
- NeoForge **26.2.0.75**
- ModDevGradle **2.0.146**
- Gradle **9.2.1**
- Java **25**
- Mod ID `dmonsters`

## 当前状态

源码、玩法内容、客户端渲染、资源和数据迁移已经完成

运行时和实际游玩验证单独记录在 [`测试清单.md`](测试清单.md)

已完成内容包括

- 12 种原版怪物及对应实体类型、属性、AI 和渲染
- 12 种原版怪物刷怪蛋
- 原版方块和特殊行为
- Rebar、四种 Harpoon、Lucky Egg、Dagon 与原版怪物掉落功能物品
- Lucky Egg 与 Dagon 投射物
- 原版贴图与声音
- 当前格式的 blockstate、方块模型、物品模型与 item definition
- 17 个原版合成配方
- 当前格式的实体与方块战利品表
- 怪物生命、攻击、速度、自然生成权重和禁用开关配置
- Mutant Steve、Unborn Baby、Haunted Cow、Topielec 专用配置
- 通过 NeoForge biome modifier 实现的自然生成

更详细的移植决策见 [`移植说明.md`](移植说明.md)

## 配方查看与信息显示兼容性

本模组没有自定义配方类型、配方容器或特殊工作台，现有合成全部走 Minecraft 原生配方系统

因此 JEI、REI 会直接读取这些原生配方，不需要额外桥接层

Jade 会通过标准方块、实体注册信息读取本模组内容，也不需要专用 provider 才能显示基础信息

EMI 截至 2026-09-06 尚未提供 Minecraft 26.2 版本，因此当前无法进行 26.2 实机联合验证，本模组不对 EMI 添加硬依赖或临时兼容层，继续保持标准数据结构，等待 EMI 提供 26.2 构建后即可直接复验

完整兼容性说明见 [`兼容性.md`](兼容性.md)

## 开发运行

本仓库当前使用系统 Gradle 9.2.1，不包含 Gradle Wrapper

```bash
gradle runClient
gradle runServer
gradle runData
gradle build
```

开发和构建统一使用 JDK 25

## 配置

NeoForge 首次运行后会生成 Deadly Monsters 公共配置

移植版保留原版默认怪物倍率和自然生成权重

`spawnRate` 与 `disabled` 会影响生物群系生成列表，修改后建议重启世界或专用服务器

## 移植原则

这是原生移植，不是旧版兼容垫片

少量 1.12.2 实现细节无法或不应该逐字复制

- 不再接入已经过时的 Hostile Worlds Invasions 旧版联动
- 旧版数字 `dayLengthTicks` 已退役，Sunlight Drop 与 Haunted Cow 使用 Minecraft 26.2 当前世界时钟标记
- Topielec 原版深水搜索存在近似每 tick 大范围扫描问题，移植版保留可见行为并限制刷新频率
- 旧 metadata 物品映射到当前独立物品 ID
- 方块修改类物品使用当前交互权限检查，避免绕过现代保护逻辑

## 上游与许可

原项目 [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters)

上游使用 MIT License，本仓库保留原始版权与许可文本 [`LICENSE`](LICENSE)
