package ia.moying.interview.homework;

public enum BreadKind {

    WHOLE_WHEAT("全麦面包"),

    MIXED_GRAIN("杂粮面包"),
    TUNA_SANDWICH("金枪鱼三明治");
    private final String name;

    BreadKind(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
