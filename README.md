# Mycoscape

A NeoForge 1.21.11 Minecraft mod, forked from the legacy Mycoscape tech project, refocused entirely on environmental worldgen, a fungal forest biome, and a full decorative block palette built around mushrooms and rotwood.

## What it adds

**Fungal Forest biome**: a rare, eerie Overworld biome with a dark color palette, mycelium particle ambience, and soul sand valley atmosphere.

**Rotwood wood set**: a full vanilla-parity wood set (log, stripped log, wood, stripped wood, planks, stairs, slab, fence, fence gate, door, trapdoor, button, pressure plate) in a corrupted dark wood style.

**Mushroom blocks**: four species with grow stages (Blewit, Morel, Jack O'Lantern, Necroshroom), giant mushroom cap blocks for each species, and a shared mushroom stem block.

**Terrain blocks**: Fungal Substrate, Mycoslate, Necroshroom Hyphae, Jack O'Lantern Vein.

**Fungal Conduit**: when powered by redstone, converts nearby logs into rotwood within a configurable radius (not fully added).

**Mushroom food items**: Blewit, Morel, Jack O'Lantern Mushroom (not food yet).

## To Do (pre-release)

### Textures
- [ ] Full rework of all existing placeholder textures (fungus grow stages, terrain blocks, mushroom caps)
- [ ] New item textures: Mycelium Fabric, Death Vine, dried mushroom variants
- [ ] Rotwood door and trapdoor texture polish

### New blocks & items
- [ ] **Food items**: recipes, food values, and possibly custom effects
- [ ] **Mycelium Fabric**: craftable from mushrooms, used as a wool substitute for easy wool collection 
- [ ] **Death Vine**: crafted from Necroshroom + Necroshroom Hyphae; behaves like a cobweb but deals damage over time to anything caught in it

### Features & worldgen
- [x] Fungal Forest placed features wired into the biome generation settings (mushroom clusters, rotwood trees)
- [x] Custom rotwood tree feature using the existing `WallVeinFeature`
- [x] Surface biome containing primarily blewit trees with occasional morel trees
- [ ] Jack o'Lantern mushrooms in the caves
- [ ] Ground is fungal substrate
- [ ] Ores in cave

### Release prep
- [ ] `LICENSE`
- [ ] `pack.png` mod icon (256×256)
- [ ] CurseForge + Modrinth listings with screenshots
