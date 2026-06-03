import json
from pathlib import Path

DYE_COLORS = [
    "white",
    "orange",
    "magenta",
    "lightblue",
    "yellow",
    "lime",
    "pink",
    "gray",
    "silver",
    "cyan",
    "purple",
    "blue",
    "brown",
    "green",
    "red",
    "black",
]

MODELS = {
    "bookshelf": {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": "minecraft:block/planks/{color}",
            "side": "dyables:block/bookshelf/side_{color}"
        }
    },

    "workbench": {
      "parent": "minecraft:block/cube_bottom_top",
      "textures": {
        "top": "dyables:block/workbench/{color}/top",
        "bottom": "minecraft:block/planks/{color}",
        "north": "dyables:block/workbench/{color}/front",
        "south": "dyables:block/workbench/{color}/front",
        "west": "dyables:block/workbench/{color}/side",
        "east": "dyables:block/workbench/{color}/side"
      }
    },

    "seat": {
      "parent": "minecraft:block/seat",
      "textures": {
        "side": "dyables:block/bed/{color}/foot_front",
        "top": "dyables:block/seat/top_{color}",
      }
    },
}

TRAPDOOR_GLASS_MODELS = {
    "top": {
        "renderlayer": 1,
        "parent": "minecraft:block/trapdoor/top",
        "textures": {
            "texture": "dyables:block/glass/{color}/none",
            "side": "dyables:block/trapdoor/glass_{color}_side"
        }
    },
    "bottom": {
        "renderlayer": 1,
        "parent": "minecraft:block/trapdoor/bottom",
        "textures": {
            "texture": "dyables:block/glass/{color}/none",
            "side": "dyables:block/trapdoor/glass_{color}_side"
        }
    },
    "open": {
        "renderlayer": 1,
        "parent": "minecraft:block/trapdoor/open",
        "textures": {
            "texture": "dyables:block/glass/{color}/none",
            "side": "dyables:block/trapdoor/glass_{color}_side"
        }
    }
}

DOOR_GLASS_MODELS = {
    "top": {
        "renderlayer": 1,
        "parent": "minecraft:block/trapdoor/top",
        "textures": {
            "texture": "dyables:block/glass/{color}/none",
            "side": "dyables:block/trapdoor/glass_{color}_side"
        }
    },
    "bottom": {
        "renderlayer": 1,
        "parent": "minecraft:block/trapdoor/bottom",
        "textures": {
            "texture": "dyables:block/glass/{color}/none",
            "side": "dyables:block/trapdoor/glass_{color}_side"
        }
    },
    "open": {
        "renderlayer": 1,
        "parent": "minecraft:block/trapdoor/open",
        "textures": {
            "texture": "dyables:block/glass/{color}/none",
            "side": "dyables:block/trapdoor/glass_{color}_side"
        }
    }
}

BED_MODELS = {
    "head": {
        "parent": "minecraft:block/bed/head",
           "textures": {
            "bottom": "minecraft:block/planks/{color}",
            "front": "minecraft:block/bed/head_front",
            "side": "dyables:block/bed/{color}/head_side",
            "top": "dyables:block/bed/{color}/head_top"
        }
    },
    "foot": {
        "parent": "minecraft:block/bed/foot",
           "textures": {
            "bottom": "minecraft:block/planks/{color}",
            "front": "dyables:block/bed/{color}/foot_front",
            "side": "dyables:block/bed/{color}/foot_side",
            "top": "dyables:block/bed/{color}/foot_top"
        }
    }
}

def replace_strings(obj, color):
    if isinstance(obj, dict):
        return {k: replace_strings(v, color) for k, v in obj.items()}
    if isinstance(obj, list):
        return [replace_strings(v, color) for v in obj]
    if isinstance(obj, str):
        return obj.format(color=color)
    return obj


for model_name, model in MODELS.items():
    output_dir = Path(model_name)
    output_dir.mkdir(parents=True, exist_ok=True)

    for color in DYE_COLORS:
        data = replace_strings(model, color)

        with open(output_dir / f"{color}.json", "w", encoding="utf-8") as file:
            json.dump(data, file, indent="\t")
            
for color in DYE_COLORS:
    color_dir = Path("trapdoor") / "glass" / color
    color_dir.mkdir(parents=True, exist_ok=True)

    for state, model in TRAPDOOR_GLASS_MODELS.items():
        data = replace_strings(model, color)

        with open(color_dir / f"{state}.json", "w", encoding="utf-8") as file:
            json.dump(data, file, indent="\t")
            
for color in DYE_COLORS:
    color_dir = Path("door") / "glass" / color
    color_dir.mkdir(parents=True, exist_ok=True)

    for half in ("top", "bottom"):
        for side in ("left", "right"):
            for open_state in ("", "_open"):
                name = f"{half}_{side}{open_state}"

                data = {
                    "renderlayer": 1,
                    "parent": f"minecraft:block/door/{half}_{side}{open_state}",
                    "textures": {
                        f"{half}": f"dyables:block/glass/{color}/{"up" if half == "bottom" else "down"}"
                    }
                }

                with open(color_dir / f"{name}.json", "w", encoding="utf-8") as file:
                    json.dump(data, file, indent="\t")

for color in DYE_COLORS:
    if color == "red":
        continue
    
    color_dir = Path("bed") / color
    color_dir.mkdir(parents=True, exist_ok=True)

    for model_name, model in BED_MODELS.items():
        data = replace_strings(model, color)

        with open(color_dir / f"{model_name}.json", "w", encoding="utf-8") as file:
            json.dump(data, file, indent="\t")