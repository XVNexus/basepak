package dev.xveon.basepak.base;

import java.util.*;

public class Argument {
    private Datatype datatype;
    private Object value;

    public Argument(Object value) {
        if (value instanceof Argument valueArg) {
            value = valueArg.value;
        }
        if (value == null) {
            this.datatype = Datatype.NUL;
            this.value = null;
        } else if (value instanceof Boolean) {
            this.datatype = Datatype.BOL;
            this.value = value;
        } else if (value instanceof Integer) {
            this.datatype = Datatype.I32;
            this.value = value;
        } else if (value instanceof Long) {
            this.datatype = Datatype.I64;
            this.value = value;
        } else if (value instanceof Float) {
            this.datatype = Datatype.F32;
            this.value = value;
        } else if (value instanceof Double) {
            this.datatype = Datatype.F64;
            this.value = value;
        } else if (value instanceof Character) {
            this.datatype = Datatype.CHR;
            this.value = value;
        } else if (value instanceof String) {
            this.datatype = Datatype.STR;
            this.value = value;
        } else if (value instanceof List<?> list) {
            this.datatype = Datatype.VEC;
            ArrayList<Argument> vec = new ArrayList<>();
            this.value = vec;
            for (Object item : list) {
                vec.add(new Argument(item));
            }
        } else if (value instanceof Map<?, ?> valueMap) {
            this.datatype = Datatype.MAP;
            HashMap<Argument, Argument> map = new HashMap<>();
            this.value = map;
            for (Object key : valueMap.keySet()) {
                map.put(new Argument(key), new Argument(valueMap.get(key)));
            }
        } else {
            throw new IllegalArgumentException("Argument value '" + value + "' is not of a recognized datatype.");
        }
    }

    public Argument(Datatype datatype) {
        this.datatype = datatype;
        this.value = getDefault(datatype);
    }

    public Argument to(Datatype datatype) {
        return switch (this.datatype) {
            case NUL -> nulTo(datatype);
            case BOL -> bolTo(datatype);
            case I32 -> i32To(datatype);
            case I64 -> i64To(datatype);
            case F32 -> f32To(datatype);
            case F64 -> f64To(datatype);
            case CHR -> chrTo(datatype);
            case STR -> strTo(datatype);
            case VEC -> vecTo(datatype);
            case MAP -> mapTo(datatype);
        };
    }

    private Argument nulTo(Datatype datatype) {
        return new Argument(datatype);
    }

    private Argument bolTo(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> value;
            case I32 -> (boolean) value ? 1 : 0;
            case I64 -> (boolean) value ? 1L : 0L;
            case F32 -> (boolean) value ? 1f : 0f;
            case F64 -> (boolean) value ? 1D : 0D;
            case CHR -> (boolean) value ? '#' : ' ';
            case STR -> value.toString();
            case VEC -> List.of((boolean) value);
            case MAP -> {
                Map<Boolean, Boolean> map = new HashMap<>();
                map.put(false, (boolean) value);
                yield map;
            }
        });
    }

    private Argument i32To(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> (int) value > 0;
            case I32 -> value;
            case I64 -> (long) (int) value;
            case F32 -> (float) (int) value;
            case F64 -> (double) (int) value;
            case CHR -> (char) ((int) value + 32);
            case STR -> value.toString();
            case VEC -> List.of((int) value);
            case MAP -> {
                Map<Boolean, Integer> map = new HashMap<>();
                map.put(false, (int) value);
                yield map;
            }
        });
    }

    private Argument i64To(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> (long) value > 0;
            case I32 -> (int) (long) value;
            case I64 -> value;
            case F32 -> (float) (long) value;
            case F64 -> (double) (long) value;
            case CHR -> (char) ((long) value + 32);
            case STR -> value.toString();
            case VEC -> List.of((long) value);
            case MAP -> {
                Map<Boolean, Long> map = new HashMap<>();
                map.put(false, (long) value);
                yield map;
            }
        });
    }

    private Argument f32To(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> (float) value > 0f;
            case I32 -> (int) (float) value;
            case I64 -> (long) (float) value;
            case F32 -> value;
            case F64 -> (double) (float) value;
            case CHR -> (char) ((float) value + 32);
            case STR -> value.toString();
            case VEC -> List.of((float) value);
            case MAP -> {
                Map<Boolean, Float> map = new HashMap<>();
                map.put(false, (float) value);
                yield map;
            }
        });
    }

    private Argument f64To(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> (double) value > 0f;
            case I32 -> (int) (double) value;
            case I64 -> (long) (double) value;
            case F32 -> (float) (double) value;
            case F64 -> value;
            case CHR -> (char) ((double) value + 32);
            case STR -> value.toString();
            case VEC -> List.of((double) value);
            case MAP -> {
                Map<Boolean, Double> map = new HashMap<>();
                map.put(false, (double) value);
                yield map;
            }
        });
    }

    private Argument chrTo(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> (char) value > ' ';
            case I32 -> (int) (char) value - 32;
            case I64 -> (long) (char) value - 32;
            case F32 -> (float) (char) value - 32;
            case F64 -> (double) (char) value - 32;
            case CHR -> value;
            case STR -> value.toString();
            case VEC -> List.of((char) value);
            case MAP -> {
                Map<Boolean, Character> map = new HashMap<>();
                map.put(false, (char) value);
                yield map;
            }
        });
    }

    private Argument strTo(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> !((String) value).isEmpty();
            case I32 -> ((String) value).length();
            case I64 -> (long) ((String) value).length();
            case F32 -> (float) ((String) value).length();
            case F64 -> (double) ((String) value).length();
            case CHR -> {
                String valueString = ((String) value);
                yield !valueString.isEmpty() ? valueString.charAt(0) : ' ';
            }
            case STR -> value;
            case VEC -> List.of((String) value);
            case MAP -> {
                Map<Boolean, String> map = new HashMap<>();
                map.put(false, (String) value);
                yield map;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Argument vecTo(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> !((ArrayList<Argument>) value).isEmpty();
            case I32 -> ((ArrayList<Argument>) value).size();
            case I64 -> (long) ((ArrayList<Argument>) value).size();
            case F32 -> (float) ((ArrayList<Argument>) value).size();
            case F64 -> (double) ((ArrayList<Argument>) value).size();
            case CHR -> (char) (((ArrayList<Argument>) value).size() + 32);
            case STR -> value.toString();
            case VEC -> value;
            case MAP -> {
                Map<Integer, Argument> valueMap = new HashMap<>();
                ArrayList<Argument> valueList = (ArrayList<Argument>) value;
                for (int i = 0; i < valueList.size(); i++) {
                    valueMap.put(i, valueList.get(i));
                }
                yield valueMap;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Argument mapTo(Datatype datatype) {
        return new Argument(switch (datatype) {
            case NUL -> null;
            case BOL -> !((HashMap<Argument, Argument>) value).isEmpty();
            case I32 -> ((HashMap<Argument, Argument>) value).size();
            case I64 -> (long) ((HashMap<Argument, Argument>) value).size();
            case F32 -> (float) ((HashMap<Argument, Argument>) value).size();
            case F64 -> (double) ((HashMap<Argument, Argument>) value).size();
            case CHR -> (char) (((HashMap<Argument, Argument>) value).size() + 32);
            case STR -> value.toString();
            case VEC -> new ArrayList<>(((HashMap<Argument, Argument>) value).values());
            case MAP -> value;
        });
    }

    public Datatype getDatatype() {
        return datatype;
    }

    public Object getValue() {
        return value;
    }

    public boolean enforceType(Datatype datatype, String functionName, String argumentName) {
        if (this.datatype == Datatype.NUL) {
            this.datatype = datatype;
            this.value = getDefault(datatype);
            return true;
        } else if (this.datatype != datatype) {
            return false;
        }
        return false;
    }

    public Object getValueEnforced(Datatype datatype, String functionName, String argumentName) {
        if (this.datatype == Datatype.NUL) {
            throw new IllegalArgumentException("Argument value is null.");
        }
        if (!enforceType(datatype, functionName, argumentName)) {
            throw new IllegalArgumentException(datatype + " is not a valid argument type for '" + functionName + ":" + argumentName + "'");
        }
        return value;
    }

    public Object getValueEnforced(Datatype datatype, Object defaultValue, String functionName, String argumentName) {
        if (this.datatype == Datatype.NUL) {
            return defaultValue;
        }
        if (!enforceType(datatype, functionName, argumentName)) {
            throw new IllegalArgumentException(datatype + " is not a valid argument type for '" + functionName + ":" + argumentName + "'");
        }
        return value;
    }

    public static Object getDefault(Datatype datatype) {
        return switch (datatype) {
            case NUL -> null;
            case BOL -> false;
            case I32 -> 0;
            case I64 -> 0L;
            case F32 -> 0f;
            case F64 -> 0D;
            case CHR -> ' ';
            case STR -> "";
            case VEC -> new ArrayList<>();
            case MAP -> new HashMap<>();
        };
    }

    @Override
    public String toString() {
        return this.datatype.toString().toLowerCase() + (this.value != null ? ":" + this.value.toString() : "");
    }
}
