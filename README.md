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
- `grab <bsp_path>` Import another bsp file stored at *bsp_path*, adding all the functions from that file to the current file.
- `goto [fun_name] [ins_index]` Move execution pointer to function and line number *fun_name:ins_index*. If *fun_name* is not specified, this will jump within the current function; if *ins_index* is not specified, this will jump to the first instruction of the specified function. This does not return the execution pointer to the original *goto* call after reaching the end of a function like *call* does. This function will only operate if the value stored in the *loc.test* register is true, ensuring that it can be used with *test* to control program flow.
- `move [reg_from] <reg_to>` Copy the value stored in register *reg_from* to register *reg_to*. The main namespaces are as follows: *loc.* for script and function local variables, *api.* for Minecraft API data, and *com.* for Minecraft chat command context. If *nul* is provided for *reg_from*, *reg_to* will be deleted.
- `call <fun_name> [arg_list]` Call the function *fun_name*, passing it the arguments *arg_list*. This will execute all instructions in the function before returning execution to after the original *call* instruction. The main namespaces are as follows: *loc.* for script functions, *api.* for Minecraft API functions, and *com.* for Minecraft chat commands. Output of *api.* calls will be written to the register *api.out*, or *api.err* if the API call fails. Output of *com.* calls will be written to the register *com.out*, or *com.err* if the command fails. For any *com.* calls, dots and spaces can be used interchangeably and are interpreted identically.
- `test <reg_a> [condition] [reg_b]` Compare the values in *reg_a* and *reg_b* against *condition*, writing the comparison result to the register *loc.test*.
- `calc <reg_a> [operation] [reg_b]` Run a mathematical operation on the values in *reg_a* and *reg_b*, writing the calculation result to the register *loc.calc*.
- `wait <delay>` Synchronously pause execution for *delay* game ticks.
- `iter <interval> <fun_name> [arg_list]` Register the function *fun_name* to be called every *interval* game ticks.
- `stop <scope>` Terminate execution within the specified scope. Scope can be as follows: *fun_name* for an iterating function, *evn* for the current event callback thread, *pac* for all code from the current datapack, and *eng* for all code currently loaded in the script engine.
- `plog [level] [message]` Send a message *message* to the game logs and in-game chat with the severity level *level*. Severity level can be as follows: *inf* for a regular message, *war* for abnormal behavior, and *err* for a failure.
- `noop` No operation.
- `blor` Pwint :3
