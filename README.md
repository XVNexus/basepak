# Basepak
An assembly-style scripting language for Minecraft.

## Registers
String-addressed object storage slots. Can be created or deleted at will, and many registers are provided by default for interacting with the game world.

## Functions
String-addressed procedures. Can be defined in the script files, and many functions are provided by default for interacting with the game world. Threads (event callbacks) can be run repeatedly up to once per game tick.

## Instructions
Single operations akin to assembly code instructions. Every function has instructions. When a function or event is called, all instructions within it will be added onto the script engine's instruction queue and executed syncrhonously. If the instruction queue exceeds a certain length, for example in the case of infinite recursion, the queue is emergency cleared and an error is thrown.

## Format Example

```
# comment

function_name:
    instruction argument

on_event:
    instruction argument

print_literals:
    plog info bol:true
    plog info i32:42
    plog info i64:42
    plog info f32:6.9
    plog info f64:6.9
    plog info chr:A
    plog info vec(chr):'Hello, world!'
    plog info vec(i32):[1,2,4,8]
    plog info map(vec(chr),i32):['one',1,'two',2,'four',4,'eight',8]

###
multi-line
comment
###
```

## Default Namespaces
- `loc` Local functions and variables.
- `api` Minecraft API.
- `com` Minecraft chat commands.
- `evn` Current thread (event callback).
- `pac` All code from the current datapack.
- `eng` The core Basepak script engine.
- `nul` Placeholder for blank values.

## Instruction Set
- `grab <bsp_path>`
- `goto <ins_index>`
- `call <ins_index> [arg_list...]`
- `move [reg_from] <reg_to>`
- `calc <operation> <reg_a> [reg_b]`
- `wait <delay>`
- `stop [scope]`
- `plog [level] [message]`
