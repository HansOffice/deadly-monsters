# Deadly Monsters: Modern — Minecraft 26.2 移植版

[![Minecraft](https://img.shields.io/badge/Minecraft-26.2-2EA44F?style=flat-square&logo=minecraft&logoColor=white)](https://minecraft.net/)
[![NeoForge](https://img.shields.io/badge/NeoForge-26.2.0.75-E37222?style=flat-square)](https://neoforged.net/)
[![Fabric](https://img.shields.io/badge/Fabric-0.19.5-CBB99E?style=flat-square)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Release](https://img.shields.io/github/v/release/HansOffice/deadly-monsters-modern?style=flat-square&color=238636)](https://github.com/HansOffice/deadly-monsters-modern/releases)
[![License](https://img.shields.io/badge/License-MIT-0969DA?style=flat-square)](LICENSE)

经典恐怖生物与防御工事模组 **Deadly Monsters: Modern** 是 Deadly Monsters 面向 Minecraft 26.2 的现代原生重构移植版

原模组作者为 **bigbang87**，1.12.2 版本维护者为 **ACGaming**；本移植版完整保留原版 12 种独特致命生物、防御工事建筑体系与特殊功能物品，底层完全按现代 Minecraft 26.2 标准原生重写，优化高频寻路计算，不引入臃肿的旧版转接层

---

## 目录

- [安装与运行](#安装与运行)
- [怪物图鉴](#怪物图鉴)
- [防御工事与特色物品](#防御工事与特色物品)
- [配置说明](#配置说明)
- [辅助模组兼容性](#辅助模组兼容性)
- [工程与技术文档](#工程与技术文档)
- [开发与构建](#开发与构建)
- [鸣谢与开源许可](#鸣谢与开源许可)

---

## 安装与运行

本模组提供针对主流加载器的独立构建版本，前往 [GitHub Releases](https://github.com/HansOffice/deadly-monsters-modern/releases) 下载对应文件（**两个版本切勿同时安装**）：

| 加载器 | 支持版本 | 文件名 | 前置要求 |
|---|---|---|---|
| **NeoForge** | 26.2.0.75+ | `dmonsters-neoforge-1.0-26.2.jar` | Java 25 运行时环境 |
| **Fabric** | 0.19.5+ | `dmonsters-fabric-1.0-26.2.jar` | Fabric API 0.159.0+26.2、Java 25 |

---

## 怪物图鉴

本移植版完整复刻原版 12 种各具危险特征的敌对生物，所有实体均已配置现代生物群系分布（Biome Modifier）与独立属性倍率：

| 实体 ID | 中文名称 | 主要生成环境 | 核心机制与致命危险 |
|---|---|---|---|
| `mutant_steve` | 突变史蒂夫 | 主世界陆地 | 动作敏捷的高速近战怪物；攻击可破坏周围方块（可配置）；白天暴露在日光下会自燃 |
| `freezer` | 霜冻异怪 | 雪原与极寒群系 | 近战附加持续缓慢效果；会冻结脚下水面并降雪；攻击状态切换为发光怒目纹理 |
| `climber` | 攀爬者 | 主世界阴暗处 | 具备垂直墙面攀爬能力；完全免疫蜘蛛网阻滞与中毒状态；困难难度可获得随机永久增益 |
| `entrail` | 粘液飞头 | 主世界陆地 | 具备重力漂浮特性；下落缓慢；受到非火焰伤害时会分裂并生成史莱姆 |
| `unborn_baby` | 腹中胎儿 | 主世界夜间 | 身型小巧移速较快；攻击附加迟缓效果；锁定时周期性使玩家致盲（可配置） |
| `fallen_leader` | 堕落领袖 | 主世界陆地 | 强力近战亡灵；成功攻击后会按伤害回血；掉落可提供超强击退效果的堕落领袖脊柱 |
| `bloody_maiden` | 血腥少女 | 阴暗洞穴深处 | 初始处于沉睡伪装；受到攻击或锁定目标后触发暴怒形态并恢复致命攻击 |
| `zombie_chicken` | 僵尸鸡 | 主世界陆地 | 敌对鸡类生物；会主动猎杀玩家和普通鸡；近战成功后可将普通鸡转化为僵尸鸡 |
| `present` | 礼盒怪 | 雪原露天环境 | 伪装为礼物盒外形；被攻击后将玩家传送入临时笼墙，并在中心刷新两只点燃的爬行者 |
| `stranger` | 陌生人 | 主世界夜间 | 神秘人形实体；刻意回避玩家视线并在暗处潜伏；特定靠近时播放冲击音效 |
| `haunted_cow` | 闹鬼牛 | 主世界夜间 | 诡异发光牛形怪物；免疫普通武器；被非允许武器攻击时会强制将世界时间颠倒为夜晚 |
| `topielec` | 异形水鬼 | 水域与河流湖泊 | 水下快速追踪玩家；近身时强行将玩家拖向更深水域；离水后尝试寻找水源（默认只受鱼叉伤害） |

---

## 防御工事与特色物品

为了抵御凶猛的致命怪物，模组提供完整的防御工事与特殊战利品体系：

### 1. 防御工事建筑

| 方块 | 获取方式 | 特性与用途 |
|---|---|---|
| **强化钢筋石头 / 强化钢筋圆石**<br>`strengthened_stone`<br>`strengthened_cobblestone` | 使用强化钢筋对普通石块 / 圆石右键强化 | 极高抗爆性能与坚硬度；潜行右键可无损回收钢筋并还原方块 |
| **铁丝网**<br>`barbed_wire` | 铁锭 + 铁粒合成 | 必须放置在有效方块表面；生物接触后受到持续接触伤害并被大幅减缓水平位移 |
| **铁围栏与围栏柱**<br>`mesh_fence`<br>`mesh_fence_pole` | 铁丝网 + 铁锭合成 | 专用铁丝网围栏连接机制；保留原版 8 格立柱跨度锚定限制，无立柱支撑时结构失效 |
| **灵魂之眼**<br>`soul_eye` | 末影之眼与黑曜石等合成 | 具备休眠、苏醒与吞噬三阶段循环；可吞噬周围靠近的生物并概率吐出绿宝石与铁锭 |
| **圣诞树与礼物盒**<br>`christmas_tree`<br>`present_box` | 云杉树苗与特定物品合成 | 圣诞树周期性在相邻位置生长礼物盒；破坏礼盒可随机开出丰厚资源或遭遇即时危险 |

### 2. 特色功能物品与武器

| 物品 | 类别 | 效果与使用方式 |
|---|---|---|
| **强化钢筋 (Rebar)** | 材料 | 强化建筑核心原料，用于加固普通石材以及合成铁丝网 |
| **四阶鱼叉 (Harpoon)** | 武器 / 工具 | 石、铁、钻石、黑曜石四种档位；水中使用成功捕获时掉落鳕鱼，也是克制异形水鬼的关键武器 |
| **幸运蛋 (Lucky Egg)** | 投掷物 | 投掷击中方块触发随机效果：奖励装备、短引信 TNT、生成普通雏鸡或生成危险僵尸鸡 |
| **达贡 (Dagon)** | 投掷物 | 附带特殊随机运动弹道的神秘投掷物 |
| **血腥少女之心** | 功能战利品 | 普通右键在目标方块生成岩浆源，潜行右键生成水源；受世界交互权限保护 |
| **堕落领袖脊柱** | 近战武器 | 命中目标时附带极强冲量物理击退效果 |
| **胎儿之眼 (Baby Eye)** | 功能战利品 | 用于安全提取目标方块结构 |
| **日光降临 (Sunlight Drop)** | 消耗品 | 仅夜间可用；使用后将主世界当前时钟直接推进至清晨白天 |

---

## 配置说明

首次启动模组后，服务端或单人客户端会在 `config/` 目录下生成公共配置文件：

* 配置文件路径：`config/dmonsters-common.toml`
* 关键配置段落：
  * **全局属性倍率**：可集中按比例调整全模组怪物的最大生命、攻击伤害与移动速度
  * **单怪独立倍率与开关**：每种怪物均拥有独立的 `healthMultiplier`、`attackMultiplier`、`speedMultiplier`、`spawnRate`（生成权重）以及 `disabled`（彻底禁用）
  * **特殊机制开关**：
    * `mutant_steve.breakBlocks`：是否允许突变史蒂夫在特殊攻击时破坏方块（默认开启）
    * `haunted_cow.disableTimeChange`：是否关闭闹鬼牛受到无效武器攻击时强制转夜的机制
    * `topielec.harpoonOnly`：是否限制异形水鬼仅能被鱼叉造成玩家直接伤害（默认关闭）
    * `unborn_baby.blindness`：是否启用腹中胎儿的周期失明光环

> [!NOTE]
> 修改 `spawnRate` 或 `disabled` 会在世界加载时影响生物群系的生成列表注入，修改后建议重新进入世界或重启专用服务器

---

## 辅助模组兼容性

本模组所有方块、物品、实体均遵循 Minecraft 标准 Registry 架构，合成配方采用纯原版 JSON 数据驱动体系：

* **JEI (Just Enough Items)**：开箱即用，可直接查询 17 个合成配方与物品用途
* **REI (Roughly Enough Items)**：开箱即用，原生配方全面兼容
* **Jade**：开箱即用，可直接读取方块与实体的基础注册元数据与血量信息
* **EMI**：截至 2026-09-06，EMI 官方尚未提供针对 Minecraft 26.2 的稳定发行版本；本模组保持纯正原版标准数据结构，不引入临时伪兼容层，等待 EMI 官方 26.2 发行后即可直接无缝读取

详细兼容性调研与测试记录参见 [docs/兼容性.md](docs/兼容性.md)

---

## 工程与技术文档

关于跨大版本重构与测试的深度技术细节，可参阅仓库内的附带文档：

- [docs/移植说明.md](docs/移植说明.md) — 1.12.2 到 26.2 的机制对照、时钟模型替换与现代化决策记录
- [docs/兼容性.md](docs/兼容性.md) — 外部辅助模组生态调研与适配原则
- [docs/测试清单.md](docs/测试清单.md) — 覆盖 12 种生物、特殊方块、音效及联机行为的手动实机验证清单

---

## 开发与构建

本项目使用系统安装的 Gradle 9.5.1 与 Java 25 进行模块化开发：

<details>
<summary>点击展开构建命令参考</summary>

```bash
# 启动客户端测试环境
gradle runClient

# 启动专用服务器测试环境
gradle runServer

# 运行数据生成器（DataGen 生成战利品表、模型与配方）
gradle runData

# 编译并输出最终 Mod Jar
gradle build
```

构建产物将输出至 `build/libs/` 目录
</details>

---

## 鸣谢与开源许可

* 原作设计与 1.10–1.12 代码：[bigbang87/deadly-monsters](https://github.com/bigbang87/deadly-monsters)
* 1.12.2 维护与修复版本：[ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters)
* Minecraft 26.2 原生重构移植：[HansOffice](https://github.com/HansOffice)

本项目遵循 [MIT License](LICENSE) 开源协议发布，完整保留原作者版权与署名文件
