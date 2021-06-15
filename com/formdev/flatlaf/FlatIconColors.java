/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf;

public final class FlatIconColors
extends Enum<FlatIconColors> {
    public static final /* enum */ FlatIconColors ACTIONS_RED = new FlatIconColors(((int)1134856376L ^ 0x43A2527B) << 5, "Actions.Red", ((int)-179522200L ^ 0xF54CB569) != 0, (boolean)((long)-804887682 ^ (long)-804887682));
    public static final /* enum */ FlatIconColors ACTIONS_RED_DARK = new FlatIconColors(((int)697306670L ^ 0x299C7B6B) << 4, "Actions.Red", ((int)-1384064250L ^ 0xAD80DB06) != 0, ((int)-137790990L ^ 0xF7C979F3) != 0);
    public static final /* enum */ FlatIconColors ACTIONS_YELLOW = new FlatIconColors(((int)1431763679L ^ 0x55568C0E) << 9, "Actions.Yellow", ((int)529050036L ^ 0x1F88A9B5) != 0, (boolean)((long)1788695415 ^ (long)1788695415));
    public static final /* enum */ FlatIconColors ACTIONS_YELLOW_DARK = new FlatIconColors(((int)109185887L ^ 0x6FA58C6) << 1, "Actions.Yellow", ((int)-2038386347L ^ 0x8680B155) != 0, (boolean)((long)54858749 ^ (long)54858748));
    public static final /* enum */ FlatIconColors ACTIONS_GREEN = new FlatIconColors((int)-1634032461L ^ 0x9EC30CDA, "Actions.Green", ((int)1488013139L ^ 0x58B14752) != 0, ((int)-607387926L ^ 0xDBCBFEEA) != 0);
    public static final /* enum */ FlatIconColors ACTIONS_GREEN_DARK = new FlatIconColors(((int)-1905368545L ^ 0x8E7C050A) << 2, "Actions.Green", (boolean)((long)-1062961366 ^ (long)-1062961366), (boolean)((long)936116992 ^ (long)936116993));
    public static final /* enum */ FlatIconColors ACTIONS_BLUE = new FlatIconColors((int)((long)-1275864707 ^ (long)-1276143978) << 1, "Actions.Blue", ((int)1223575273L ^ 0x48EE46E8) != 0, ((int)-546106963L ^ 0xDF7311AD) != 0);
    public static final /* enum */ FlatIconColors ACTIONS_BLUE_DARK = new FlatIconColors(((int)-762055833L ^ 0xD29E97D6) << 2, "Actions.Blue", ((int)2032676657L ^ 0x79282F31) != 0, (boolean)((long)-732508374 ^ (long)-732508373));
    public static final /* enum */ FlatIconColors ACTIONS_GREY = new FlatIconColors((int)((long)897571523 ^ (long)893971956) << 1, "Actions.Grey", (boolean)((long)-1335265630 ^ (long)-1335265629), ((int)-1730921315L ^ 0x98D43C9D) != 0);
    public static final /* enum */ FlatIconColors ACTIONS_GREY_DARK = new FlatIconColors((int)1472681082L ^ 0x5768E5C9, "Actions.Grey", ((int)-1900425679L ^ 0x8EB9CE31) != 0, ((int)773215972L ^ 0x2E1656E5) != 0);
    public static final /* enum */ FlatIconColors ACTIONS_GREYINLINE = new FlatIconColors((int)-1931941218L ^ 0x8CA7610F, "Actions.GreyInline", (boolean)((long)-1960395997 ^ (long)-1960395998), (boolean)((long)671928959 ^ (long)671928959));
    public static final /* enum */ FlatIconColors ACTIONS_GREYINLINE_DARK = new FlatIconColors((int)((long)157447413 ^ (long)152960868), "Actions.GreyInline", (boolean)((long)-1567477975 ^ (long)-1567477975), ((int)-2012698850L ^ 0x8808A71F) != 0);
    public static final /* enum */ FlatIconColors OBJECTS_GREY = new FlatIconColors(((int)1240356440L ^ 0x49E7FC23) << 4, "Objects.Grey");
    public static final /* enum */ FlatIconColors OBJECTS_BLUE = new FlatIconColors(((int)1151348824L ^ 0x44A235EF) << 5, "Objects.Blue");
    public static final /* enum */ FlatIconColors OBJECTS_GREEN = new FlatIconColors((int)((long)-670172807 ^ (long)-663794630), "Objects.Green");
    public static final /* enum */ FlatIconColors OBJECTS_YELLOW = new FlatIconColors((int)((long)1572309753 ^ (long)1564681668), "Objects.Yellow");
    public static final /* enum */ FlatIconColors OBJECTS_YELLOW_DARK = new FlatIconColors((int)((long)1894493397 ^ (long)1882328982), "Objects.YellowDark");
    public static final /* enum */ FlatIconColors OBJECTS_PURPLE = new FlatIconColors((int)((long)551003589 ^ (long)549492410) << 3, "Objects.Purple");
    public static final /* enum */ FlatIconColors OBJECTS_PINK = new FlatIconColors((int)((long)-1590097475 ^ (long)-1589261198) << 1, "Objects.Pink");
    public static final /* enum */ FlatIconColors OBJECTS_RED = new FlatIconColors(((int)-1458500000L ^ 0xA9683CF1) << 1, "Objects.Red");
    public static final /* enum */ FlatIconColors OBJECTS_RED_STATUS = new FlatIconColors((int)((long)1496335748 ^ (long)1506808017), "Objects.RedStatus");
    public static final /* enum */ FlatIconColors OBJECTS_GREEN_ANDROID = new FlatIconColors((int)((long)442978405 ^ (long)449025628), "Objects.GreenAndroid");
    public static final /* enum */ FlatIconColors OBJECTS_BLACK_TEXT = new FlatIconColors(((int)647060868L ^ 0x2690457D) << 5, "Objects.BlackText");
    public final int rgb;
    public final String key;
    public final boolean light;
    public final boolean dark;
    private static final /* synthetic */ FlatIconColors[] $VALUES;

    public static FlatIconColors[] values() {
        return (FlatIconColors[])$VALUES.clone();
    }

    public static FlatIconColors valueOf(String string) {
        return Enum.valueOf(FlatIconColors.class, string);
    }

    private FlatIconColors(int n3, String string2) {
        this(n3, string2, (boolean)((long)-1754730663 ^ (long)-1754730664), ((int)-589779038L ^ 0xDCD8AFA3) != 0);
    }

    private FlatIconColors(int n3, String string2, boolean bl, boolean bl2) {
        this.rgb = n3;
        this.key = string2;
        this.light = bl;
        this.dark = bl2;
    }

    static {
        FlatIconColors[] arrflatIconColors = new FlatIconColors[(int)((long)-232591952 ^ (long)-232591961)];
        arrflatIconColors[(int)((long)1588936107 ^ (long)1588936107)] = ACTIONS_RED;
        arrflatIconColors[(int)1703881444L ^ 1703881445] = ACTIONS_RED_DARK;
        arrflatIconColors[(int)((long)909408098 ^ (long)909408099) << 1] = ACTIONS_YELLOW;
        arrflatIconColors[(int)-982327544L ^ -982327541] = ACTIONS_YELLOW_DARK;
        arrflatIconColors[((int)661321747L ^ 661321746) << 2] = ACTIONS_GREEN;
        arrflatIconColors[(int)-839583381L ^ -839583378] = ACTIONS_GREEN_DARK;
        arrflatIconColors[(int)((long)-1034976368 ^ (long)-1034976365) << 1] = ACTIONS_BLUE;
        arrflatIconColors[(int)((long)789713430 ^ (long)0x2F121211)] = ACTIONS_BLUE_DARK;
        arrflatIconColors[(int)((long)-954231026 ^ (long)-954231025) << 3] = ACTIONS_GREY;
        arrflatIconColors[(int)-2035059959L ^ -2035059968] = ACTIONS_GREY_DARK;
        arrflatIconColors[((int)-997626146L ^ -997626149) << 1] = ACTIONS_GREYINLINE;
        arrflatIconColors[(int)-1815362354L ^ -1815362363] = ACTIONS_GREYINLINE_DARK;
        arrflatIconColors[(int)((long)1027742078 ^ (long)1027742077) << 2] = OBJECTS_GREY;
        arrflatIconColors[(int)-103546484L ^ -103546495] = OBJECTS_BLUE;
        arrflatIconColors[(int)((long)-1807152306 ^ (long)-1807152311) << 1] = OBJECTS_GREEN;
        arrflatIconColors[(int)((long)2021864396 ^ (long)2021864387)] = OBJECTS_YELLOW;
        arrflatIconColors[((int)-2113393395L ^ -2113393396) << 4] = OBJECTS_YELLOW_DARK;
        arrflatIconColors[(int)((long)1414152552 ^ (long)1414152569)] = OBJECTS_PURPLE;
        arrflatIconColors[(int)((long)-864946827 ^ (long)-864946820) << 1] = OBJECTS_PINK;
        arrflatIconColors[(int)1335489341L ^ 1335489326] = OBJECTS_RED;
        arrflatIconColors[((int)7347824L ^ 7347829) << 2] = OBJECTS_RED_STATUS;
        arrflatIconColors[(int)((long)1143552507 ^ (long)1143552494)] = OBJECTS_GREEN_ANDROID;
        arrflatIconColors[((int)1938738381L ^ 1938738374) << 1] = OBJECTS_BLACK_TEXT;
        $VALUES = arrflatIconColors;
    }
}

