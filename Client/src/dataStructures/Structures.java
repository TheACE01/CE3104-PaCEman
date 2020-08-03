package dataStructures;

import interfaces.Item;

public class Structures {

    // The quadrant List
    public static Quadrant director[] = {

            //row 1
            new Quadrant(0, 94, 100), new Quadrant(1, 141, 100),
            new Quadrant(2, 188, 100), new Quadrant(3, 235, 100),
            new Quadrant(4, 282, 100), new Quadrant(5, 329, 100),
            new Quadrant(6, 376, 100), new Quadrant(7, 423, 100),
            new Quadrant(8, 470, 100), new Quadrant(9, 517, 100),
            new Quadrant(10, 705, 100), new Quadrant(11, 752, 100),
            new Quadrant(12, 799, 100), new Quadrant(13, 846, 100),
            new Quadrant(14, 893, 100), new Quadrant(15, 940, 100),
            new Quadrant(16, 987, 100), new Quadrant(17, 1034, 100),
            new Quadrant(18, 1081, 100), new Quadrant(19,1128 , 100),
            new Quadrant(20, 1175, 100),

            //row 2
            new Quadrant(21, 94, 150), new Quadrant(22, 235, 150),
            new Quadrant(23, 423, 150), new Quadrant(24, 517, 150),
            new Quadrant(25, 564, 150), new Quadrant(26, 611, 150),
            new Quadrant(27, 658, 150), new Quadrant(28, 705, 150),
            new Quadrant(29, 940, 150), new Quadrant(30, 1034, 150),
            new Quadrant(31, 1175, 150),

            //row 3
            new Quadrant(32, 94, 200), new Quadrant(33, 188, 200),
            new Quadrant(34, 235, 200), new Quadrant(35, 282, 200),
            new Quadrant(36, 329, 200), new Quadrant(37, 376, 200),
            new Quadrant(38, 423, 200), new Quadrant(39, 705, 200),
            new Quadrant(40, 752, 200), new Quadrant(41, 799, 200),
            new Quadrant(42, 846, 200), new Quadrant(43, 893, 200),
            new Quadrant(44, 940, 200), new Quadrant(45, 987, 200),
            new Quadrant(46, 1034, 200), new Quadrant(47, 1081, 200),
            new Quadrant(48, 1128, 200), new Quadrant(49, 1175, 200),
            new Quadrant(50, 1081, 200), new Quadrant(51,1128 , 200),
            new Quadrant(52, 1175, 200),

            //row 4
            new Quadrant(53, 94, 250), new Quadrant(54, 188, 250),
            new Quadrant(55, 282, 250), new Quadrant(56, 423, 250),
            new Quadrant(57, 470, 250), new Quadrant(58, 517, 250),
            new Quadrant(59, 564, 250), new Quadrant(60, 611, 250),
            new Quadrant(61, 658, 250), new Quadrant(62, 705, 250),
            new Quadrant(63, 799, 250), new Quadrant(64, 1081, 250),
            new Quadrant(65, 1175, 250),

            //row 5
            new Quadrant(66, 94, 300), new Quadrant(67, 188, 300),
            new Quadrant(68, 282, 300), new Quadrant(69, 329, 300),
            new Quadrant(70, 376, 300), new Quadrant(71, 423, 300),
            new Quadrant(72, 705, 300), new Quadrant(73, 799, 300),
            new Quadrant(74, 893, 300), new Quadrant(75, 940, 300),
            new Quadrant(76, 987, 300), new Quadrant(77, 1034, 300),
            new Quadrant(78, 1081, 300), new Quadrant(79, 1175, 300),

            //row 6
            new Quadrant(80, 94, 350), new Quadrant(81, 141, 350),
            new Quadrant(82, 188, 350), new Quadrant(83, 282, 350),
            new Quadrant(84, 423, 350),
            new Quadrant(85, 705, 350), new Quadrant(86, 799, 350),
            new Quadrant(87, 846, 350), new Quadrant(88, 893, 350),
            new Quadrant(89, 1034, 350), new Quadrant(90, 1175, 350),

            //row 7
            new Quadrant(91, 94, 400), new Quadrant(92, 188, 400),
            new Quadrant(93, 235, 400), new Quadrant(94, 282, 400),
            new Quadrant(95, 329, 400), new Quadrant(96, 376, 400),
            new Quadrant(97, 423, 400), new Quadrant(98, 705, 400),
            new Quadrant(99, 846, 400), new Quadrant(100, 987, 400),
            new Quadrant(101, 1034, 400), new Quadrant(102, 1081, 400),
            new Quadrant(103, 1128, 400), new Quadrant(104, 1175, 400),

            //row 8
            new Quadrant(105, 94, 450), new Quadrant(106, 188, 450),
            new Quadrant(107, 376, 450), new Quadrant(108, 517, 450),
            new Quadrant(109, 611, 450),
            new Quadrant(110, 658, 450), new Quadrant(111, 705, 450),
            new Quadrant(112, 752, 450), new Quadrant(113, 846, 450),
            new Quadrant(114, 893, 450), new Quadrant(115, 940, 450),
            new Quadrant(116, 987, 450), new Quadrant(117, 1175, 450),

            //row 9
            new Quadrant(118, 94, 500), new Quadrant(119, 141, 500),
            new Quadrant(120, 188, 500), new Quadrant(121, 282, 500),
            new Quadrant(122, 329, 500), new Quadrant(123, 376, 500),
            new Quadrant(124, 423, 500), new Quadrant(125, 470, 500),
            new Quadrant(126, 517, 500), new Quadrant(127, 752, 500),
            new Quadrant(128, 846, 500), new Quadrant(129, 987, 500),
            new Quadrant(130, 1034, 500), new Quadrant(131, 1081, 500),
            new Quadrant(132, 1128, 500),new Quadrant(133, 1175, 500),

            //row 10
            new Quadrant(134, 94, 550), new Quadrant(135, 282, 550),
            new Quadrant(136, 470, 550), new Quadrant(137, 611, 550),
            new Quadrant(138, 658, 550), new Quadrant(139, 705, 550),
            new Quadrant(140, 752, 550), new Quadrant(141, 846, 550),
            new Quadrant(142, 1175, 550),

            //row 11
            new Quadrant(143, 94, 600), new Quadrant(144, 141, 600),
            new Quadrant(145, 188, 600), new Quadrant(146, 235, 600),
            new Quadrant(147, 282, 600), new Quadrant(148, 329, 600),
            new Quadrant(149, 376, 600), new Quadrant(150, 423, 600),
            new Quadrant(151, 470, 600), new Quadrant(152, 517, 600),
            new Quadrant(153, 564, 600), new Quadrant(154, 611, 600),
            new Quadrant(155, 752, 600), new Quadrant(156, 799, 600),
            new Quadrant(157, 846, 600), new Quadrant(158, 893, 600),
            new Quadrant(159, 940, 600), new Quadrant(160, 987, 600),
            new Quadrant(161, 1034, 600), new Quadrant(162,1081 , 600),
            new Quadrant(163, 1128, 600), new Quadrant(164, 1128, 600),
            new Quadrant(165, 1175, 600)};

    public static GhostNode ghostGraph[] = {
            new GhostNode(94, 100, "Node_0", "None", "Node_5", "Node_1", "None"),
            new GhostNode(235, 100, "Node_1", "None", "Node_2", "Node_23", "Node_0"),
            new GhostNode(235, 200, "Node_2", "Node_1", "None", "Node_20", "Node_3"),
            new GhostNode(188, 200, "Node_3", "None", "Node_4", "Node_2", "None"),
            new GhostNode(188, 350, "Node_4", "Node_3", "Node_8", "None", "Node_5"),
            new GhostNode(94, 350, "Node_5", "Node_0", "Node_6", "Node_4", "None"),
            new GhostNode(94, 500, "Node_6", "Node_5", "Node_9", "Node_7", "None"),
            new GhostNode(188, 500, "Node_7", "Node_8", "None", "None", "Node_6"),
            new GhostNode(188, 400, "Node_8", "Node_4", "Node_7", "Node_15", "None"),
            new GhostNode(94, 600, "Node_9", "Node_6", "None", "Node_10", "None"),
            new GhostNode(282, 600, "Node_10", "Node_11", "None", "Node_13", "Node_9"),
            new GhostNode(282, 500, "Node_11", "None", "Node_10", "Node_12", "None"),
            new GhostNode(376, 500, "Node_12", "Node_16", "None", "Node_14", "Node_11"),
            new GhostNode(470, 600, "Node_13", "Node_14", "None", "Node_36", "Node_10"),
            new GhostNode(470, 500, "Node_14", "None", "Node_13", "Node_33", "Node_12"),
            new GhostNode(282, 400, "Node_15", "Node_18", "None", "Node_16", "Node_8"),
            new GhostNode(376, 400, "Node_16", "None", "Node_12", "Node_17", "Node_15"),

            new GhostNode(423, 400, "Node_17", "Node_19", "None", "None", "Node_16"),

            new GhostNode(282, 300, "Node_18", "Node_20", "Node_15", "Node_19", "None"),

            new GhostNode(423, 300, "Node_19", "Node_21", "Node_17", "None", "Node_18"),

            new GhostNode(282, 200, "Node_20", "None", "Node_18", "Node_22", "Node_2"),

            new GhostNode(423, 250, "Node_21", "Node_22", "Node_19", "Node_28", "None"),

            new GhostNode(423, 200, "Node_22", "Node_23", "Node_21", "None", "Node_20"),

            new GhostNode(423, 100, "Node_23", "None", "Node_22", "Node_24", "Node_1"),

            new GhostNode(517, 100, "Node_24", "None", "Node_25", "None", "Node_23"),

            new GhostNode(517, 150, "Node_25", "Node_24", "None", "Node_26", "None"),

            new GhostNode(705, 150, "Node_26", "Node_27", "Node_41", "None", "Node_25"),

            new GhostNode(705, 100, "Node_27", "None", "Node_26", "Node_43", "None"),

            new GhostNode(564, 250, "Node_28", "None", "Node_29", "Node_40", "Node_21"),

            new GhostNode(564, 350, "Node_29", "Node_28", "None", "Node_30", "Node_31"),

            new GhostNode(611, 350, "Node_30", "None", "None", "None", "Node_29"),

            new GhostNode(517, 350, "Node_31", "None", "None", "Node_29", "None"),

            new GhostNode(517, 450, "Node_32", "None", "Node_33", "Node_34", "None"),

            new GhostNode(517, 500, "Node_33", "Node_32", "None", "None", "Node_14"),

            new GhostNode(705, 450, "Node_34", "Node_40", "None", "Node_39", "Node_32"),

            new GhostNode(611, 550, "Node_35", "None", "Node_36", "Node_37", "None"),

            new GhostNode(611, 600, "Node_36", "Node_35", "None", "None", "Node_13"),

            new GhostNode(752, 550, "Node_37", "Node_39", "Node_38", "None", "Node_35"),

            new GhostNode(752, 600, "Node_38", "Node_37", "None", "Node_57", "None"),

            new GhostNode(752, 450, "Node_39", "None", "Node_37", "None", "Node_34"),

            new GhostNode(705, 250, "Node_40", "Node_41", "Node_34", "None", "Node_28"),

            new GhostNode(705, 200, "Node_41", "Node_26", "Node_40", "Node_42", "None"),

            new GhostNode(799, 200, "Node_42", "None", "Node_55", "Node_44", "Node_41"),

            new GhostNode(940, 100, "Node_43", "None", "Node_44", "Node_45", "Node_27"),

            new GhostNode(940, 200, "Node_44", "Node_43", "None", "Node_46", "Node_42"),

            new GhostNode(1034, 100, "Node_45", "None", "Node_46", "Node_47", "Node_43"),

            new GhostNode(1034, 200, "Node_46", "Node_45", "None", "Node_49", "Node_44"),

            new GhostNode(1175, 100, "Node_47", "None", "Node_48", "None", "Node_45"),

            new GhostNode(1175, 200, "Node_48", "Node_47", "Node_63", "None", "Node_49"),

            new GhostNode(1081, 200, "Node_49", "None", "Node_50", "Node_48", "Node_46"),

            new GhostNode(1081, 300, "Node_50", "Node_49", "None", "None", "Node_51"),

            new GhostNode(1034, 300, "Node_51", "None", "Node_62", "Node_50", "Node_52"),

            new GhostNode(893, 300, "Node_52", "None", "Node_53", "Node_51", "None"),

            new GhostNode(893, 350, "Node_53", "Node_52", "None", "None", "Node_54"),

            new GhostNode(846, 350, "Node_54", "None", "Node_56", "Node_53", "Node_55"),

            new GhostNode(799, 350, "Node_55", "Node_42", "None", "Node_54", "None"),

            new GhostNode(846, 450, "Node_56", "Node_54", "Node_57", "Node_64", "None"),

            new GhostNode(846, 600, "Node_57", "Node_56", "None", "Node_59", "Node_38"),

            new GhostNode(987, 500, "Node_58", "Node_64", "None", "Node_60", "None"),

            new GhostNode(1175, 600, "Node_59", "Node_60", "None", "None", "Node_57"),

            new GhostNode(1175, 500, "Node_60", "Node_63", "Node_59", "None", "Node_58"),

            new GhostNode(987, 400, "Node_61", "None", "Node_64", "Node_62", "None"),

            new GhostNode(1034, 400, "Node_62", "Node_51", "None", "Node_63", "Node_61"),

            new GhostNode(1175, 400, "Node_63", "Node_48", "Node_60", "None", "Node_62"),

            new GhostNode(987, 450, "Node_64", "Node_61", "Node_58", "None", "Node_56"),
    };


    public static int getPacDotQuadrant(Item pacDot){
        for(int i = 0; i < director.length; i++){
            if(pacDot.getX() == director[i].getX() && pacDot.getY() == director[i].getY()){
                return director[i].getQuadrant();
            }
        }
        return 0;
    }


}
