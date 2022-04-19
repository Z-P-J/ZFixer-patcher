////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package org.jf.smali;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Stack;
//import org.antlr.runtime.BaseRecognizer;
//import org.antlr.runtime.BitSet;
//import org.antlr.runtime.CommonToken;
//import org.antlr.runtime.DFA;
//import org.antlr.runtime.EarlyExitException;
//import org.antlr.runtime.FailedPredicateException;
//import org.antlr.runtime.IntStream;
//import org.antlr.runtime.MismatchedSetException;
//import org.antlr.runtime.NoViableAltException;
//import org.antlr.runtime.Parser;
//import org.antlr.runtime.ParserRuleReturnScope;
//import org.antlr.runtime.RecognitionException;
//import org.antlr.runtime.RecognizerSharedState;
//import org.antlr.runtime.Token;
//import org.antlr.runtime.TokenStream;
//import org.antlr.runtime.tree.CommonTree;
//import org.antlr.runtime.tree.CommonTreeAdaptor;
//import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
//import org.antlr.runtime.tree.RewriteRuleTokenStream;
//import org.antlr.runtime.tree.TreeAdaptor;
//import org.jf.dexlib2.Opcodes;
//
//public class smaliParser extends Parser {
//    public static final String[] tokenNames = new String[]{"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACCESS_SPEC", "ANNOTATION_DIRECTIVE", "ANNOTATION_VISIBILITY", "ARRAY_DATA_DIRECTIVE", "ARRAY_TYPE_PREFIX", "ARROW", "BOOL_LITERAL", "BYTE_LITERAL", "CATCHALL_DIRECTIVE", "CATCH_DIRECTIVE", "CHAR_LITERAL", "CLASS_DESCRIPTOR", "CLASS_DIRECTIVE", "CLOSE_BRACE", "CLOSE_PAREN", "COLON", "COMMA", "DOTDOT", "DOUBLE_LITERAL", "DOUBLE_LITERAL_OR_ID", "END_ANNOTATION_DIRECTIVE", "END_ARRAY_DATA_DIRECTIVE", "END_FIELD_DIRECTIVE", "END_LOCAL_DIRECTIVE", "END_METHOD_DIRECTIVE", "END_PACKED_SWITCH_DIRECTIVE", "END_PARAMETER_DIRECTIVE", "END_SPARSE_SWITCH_DIRECTIVE", "END_SUBANNOTATION_DIRECTIVE", "ENUM_DIRECTIVE", "EPILOGUE_DIRECTIVE", "EQUAL", "FIELD_DIRECTIVE", "FIELD_OFFSET", "FLOAT_LITERAL", "FLOAT_LITERAL_OR_ID", "IMPLEMENTS_DIRECTIVE", "INLINE_INDEX", "INSTRUCTION_FORMAT10t", "INSTRUCTION_FORMAT10x", "INSTRUCTION_FORMAT10x_ODEX", "INSTRUCTION_FORMAT11n", "INSTRUCTION_FORMAT11x", "INSTRUCTION_FORMAT12x", "INSTRUCTION_FORMAT12x_OR_ID", "INSTRUCTION_FORMAT20bc", "INSTRUCTION_FORMAT20t", "INSTRUCTION_FORMAT21c_FIELD", "INSTRUCTION_FORMAT21c_FIELD_ODEX", "INSTRUCTION_FORMAT21c_LAMBDA", "INSTRUCTION_FORMAT21c_METHOD", "INSTRUCTION_FORMAT21c_STRING", "INSTRUCTION_FORMAT21c_TYPE", "INSTRUCTION_FORMAT21ih", "INSTRUCTION_FORMAT21lh", "INSTRUCTION_FORMAT21s", "INSTRUCTION_FORMAT21t", "INSTRUCTION_FORMAT22b", "INSTRUCTION_FORMAT22c_FIELD", "INSTRUCTION_FORMAT22c_FIELD_ODEX", "INSTRUCTION_FORMAT22c_STRING", "INSTRUCTION_FORMAT22c_TYPE", "INSTRUCTION_FORMAT22cs_FIELD", "INSTRUCTION_FORMAT22s", "INSTRUCTION_FORMAT22s_OR_ID", "INSTRUCTION_FORMAT22t", "INSTRUCTION_FORMAT22x", "INSTRUCTION_FORMAT23x", "INSTRUCTION_FORMAT25x", "INSTRUCTION_FORMAT30t", "INSTRUCTION_FORMAT31c", "INSTRUCTION_FORMAT31i", "INSTRUCTION_FORMAT31i_OR_ID", "INSTRUCTION_FORMAT31t", "INSTRUCTION_FORMAT32x", "INSTRUCTION_FORMAT35c_METHOD", "INSTRUCTION_FORMAT35c_METHOD_ODEX", "INSTRUCTION_FORMAT35c_TYPE", "INSTRUCTION_FORMAT35mi_METHOD", "INSTRUCTION_FORMAT35ms_METHOD", "INSTRUCTION_FORMAT3rc_METHOD", "INSTRUCTION_FORMAT3rc_METHOD_ODEX", "INSTRUCTION_FORMAT3rc_TYPE", "INSTRUCTION_FORMAT3rmi_METHOD", "INSTRUCTION_FORMAT3rms_METHOD", "INSTRUCTION_FORMAT51l", "INTEGER_LITERAL", "INVALID_TOKEN", "I_ACCESS_LIST", "I_ANNOTATION", "I_ANNOTATIONS", "I_ANNOTATION_ELEMENT", "I_ARRAY_ELEMENTS", "I_ARRAY_ELEMENT_SIZE", "I_CATCH", "I_CATCHALL", "I_CATCHES", "I_CLASS_DEF", "I_ENCODED_ARRAY", "I_ENCODED_ENUM", "I_ENCODED_FIELD", "I_ENCODED_METHOD", "I_END_LOCAL", "I_EPILOGUE", "I_FIELD", "I_FIELDS", "I_FIELD_INITIAL_VALUE", "I_FIELD_TYPE", "I_IMPLEMENTS", "I_LABEL", "I_LINE", "I_LOCAL", "I_LOCALS", "I_METHOD", "I_METHODS", "I_METHOD_PROTOTYPE", "I_METHOD_RETURN_TYPE", "I_ORDERED_METHOD_ITEMS", "I_PACKED_SWITCH_ELEMENTS", "I_PACKED_SWITCH_START_KEY", "I_PARAMETER", "I_PARAMETERS", "I_PARAMETER_NOT_SPECIFIED", "I_PROLOGUE", "I_REGISTERS", "I_REGISTER_LIST", "I_REGISTER_RANGE", "I_RESTART_LOCAL", "I_SOURCE", "I_SPARSE_SWITCH_ELEMENTS", "I_STATEMENT_ARRAY_DATA", "I_STATEMENT_FORMAT10t", "I_STATEMENT_FORMAT10x", "I_STATEMENT_FORMAT11n", "I_STATEMENT_FORMAT11x", "I_STATEMENT_FORMAT12x", "I_STATEMENT_FORMAT20bc", "I_STATEMENT_FORMAT20t", "I_STATEMENT_FORMAT21c_FIELD", "I_STATEMENT_FORMAT21c_LAMBDA", "I_STATEMENT_FORMAT21c_METHOD", "I_STATEMENT_FORMAT21c_STRING", "I_STATEMENT_FORMAT21c_TYPE", "I_STATEMENT_FORMAT21ih", "I_STATEMENT_FORMAT21lh", "I_STATEMENT_FORMAT21s", "I_STATEMENT_FORMAT21t", "I_STATEMENT_FORMAT22b", "I_STATEMENT_FORMAT22c_FIELD", "I_STATEMENT_FORMAT22c_STRING", "I_STATEMENT_FORMAT22c_TYPE", "I_STATEMENT_FORMAT22s", "I_STATEMENT_FORMAT22t", "I_STATEMENT_FORMAT22x", "I_STATEMENT_FORMAT23x", "I_STATEMENT_FORMAT25x", "I_STATEMENT_FORMAT30t", "I_STATEMENT_FORMAT31c", "I_STATEMENT_FORMAT31i", "I_STATEMENT_FORMAT31t", "I_STATEMENT_FORMAT32x", "I_STATEMENT_FORMAT35c_METHOD", "I_STATEMENT_FORMAT35c_TYPE", "I_STATEMENT_FORMAT3rc_METHOD", "I_STATEMENT_FORMAT3rc_TYPE", "I_STATEMENT_FORMAT51l", "I_STATEMENT_PACKED_SWITCH", "I_STATEMENT_SPARSE_SWITCH", "I_SUBANNOTATION", "I_SUPER", "LINE_COMMENT", "LINE_DIRECTIVE", "LOCALS_DIRECTIVE", "LOCAL_DIRECTIVE", "LONG_LITERAL", "MEMBER_NAME", "METHOD_DIRECTIVE", "NEGATIVE_INTEGER_LITERAL", "NULL_LITERAL", "OPEN_BRACE", "OPEN_PAREN", "PACKED_SWITCH_DIRECTIVE", "PARAMETER_DIRECTIVE", "PARAM_LIST_OR_ID_PRIMITIVE_TYPE", "POSITIVE_INTEGER_LITERAL", "PRIMITIVE_TYPE", "PROLOGUE_DIRECTIVE", "REGISTER", "REGISTERS_DIRECTIVE", "RESTART_LOCAL_DIRECTIVE", "SHORT_LITERAL", "SIMPLE_NAME", "SOURCE_DIRECTIVE", "SPARSE_SWITCH_DIRECTIVE", "STRING_LITERAL", "SUBANNOTATION_DIRECTIVE", "SUPER_DIRECTIVE", "VERIFICATION_ERROR_TYPE", "VOID_TYPE", "VTABLE_INDEX", "WHITE_SPACE"};
//    protected TreeAdaptor adaptor;
//    private boolean verboseErrors;
//    private boolean allowOdex;
//    private int apiLevel;
//    private Opcodes opcodes;
//    protected Stack<smaliParser.smali_file_scope> smali_file_stack;
//    protected Stack<smaliParser.statements_and_directives_scope> statements_and_directives_stack;
//    protected smaliParser.DFA30 dfa30;
//    protected smaliParser.DFA38 dfa38;
//    protected smaliParser.DFA40 dfa40;
//    static final String[] DFA30_transitionS = new String[]{"\u0001\u0002\u0001\uffff\u0001\u000e\u0003\uffff\u0001\b\f\uffff\u0001\u0007\u000f\uffff\u0001\u0006\u0002\uffff\u0001\u000f\u0001\u0010\u0001\u0011\u0001\uffff\u0001\u0012\u0001\uffff\u0001\u0013\u0002\uffff\u0001\u0014\u0001\u0015\u0001\u0018\u0001\u0019\u0001\u0016\u0001\u0017\u0003\uffff\u0001\u001a\u0001\uffff\u0001\u001b\u0001\u001c\u0001\u001e\u0001\u001d\u0001\u001f\u0001\uffff\u0001 \u0001!\u0001\uffff\u0001\"\u0001#\u0003\uffff\u0001$\u0001%\u0001\uffff\u0001&\u0001'\u0001(\u0001)\u0001*\u0005\uffff\u0001+Y\uffff\u0001,\u0001\uffff\u0001\u0005\u0001\t\u0004\uffff\u0001\u000b\u0001\u0004\u0001\f\u0001\uffff\u0001\n\u0003\uffff\u0001\u0001\u0005\uffff\u0001\u0003\u0001\r", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.\u0002\uffff\u0001\u000b", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "\u0001-¤\uffff\u0001.", "", ""};
//    static final short[] DFA30_eot = DFA.unpackEncodedString("/\uffff");
//    static final short[] DFA30_eof = DFA.unpackEncodedString("/\uffff");
//    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\u0004,\u0013\u0002\uffff");
//    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars("\u0001Ê\n¸\u0001»!¸\u0002\uffff");
//    static final short[] DFA30_accept = DFA.unpackEncodedString("-\uffff\u0001\u0001\u0001\u0002");
//    static final short[] DFA30_special = DFA.unpackEncodedString("/\uffff}>");
//    static final short[][] DFA30_transition;
//    static final String[] DFA38_transitionS;
//    static final short[] DFA38_eot;
//    static final short[] DFA38_eof;
//    static final char[] DFA38_min;
//    static final char[] DFA38_max;
//    static final short[] DFA38_accept;
//    static final short[] DFA38_special;
//    static final short[][] DFA38_transition;
//    static final String[] DFA40_transitionS;
//    static final short[] DFA40_eot;
//    static final short[] DFA40_eof;
//    static final char[] DFA40_min;
//    static final char[] DFA40_max;
//    static final short[] DFA40_accept;
//    static final short[] DFA40_special;
//    static final short[][] DFA40_transition;
//    public static final BitSet FOLLOW_class_spec_in_smali_file1095;
//    public static final BitSet FOLLOW_super_spec_in_smali_file1106;
//    public static final BitSet FOLLOW_implements_spec_in_smali_file1114;
//    public static final BitSet FOLLOW_source_spec_in_smali_file1123;
//    public static final BitSet FOLLOW_method_in_smali_file1131;
//    public static final BitSet FOLLOW_field_in_smali_file1137;
//    public static final BitSet FOLLOW_annotation_in_smali_file1143;
//    public static final BitSet FOLLOW_EOF_in_smali_file1154;
//    public static final BitSet FOLLOW_CLASS_DIRECTIVE_in_class_spec1241;
//    public static final BitSet FOLLOW_access_list_in_class_spec1243;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_class_spec1245;
//    public static final BitSet FOLLOW_SUPER_DIRECTIVE_in_super_spec1263;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_super_spec1265;
//    public static final BitSet FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1284;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1286;
//    public static final BitSet FOLLOW_SOURCE_DIRECTIVE_in_source_spec1305;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_source_spec1307;
//    public static final BitSet FOLLOW_ACCESS_SPEC_in_access_list1326;
//    public static final BitSet FOLLOW_FIELD_DIRECTIVE_in_field1357;
//    public static final BitSet FOLLOW_access_list_in_field1359;
//    public static final BitSet FOLLOW_member_name_in_field1361;
//    public static final BitSet FOLLOW_COLON_in_field1363;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field1365;
//    public static final BitSet FOLLOW_EQUAL_in_field1368;
//    public static final BitSet FOLLOW_literal_in_field1370;
//    public static final BitSet FOLLOW_annotation_in_field1383;
//    public static final BitSet FOLLOW_END_FIELD_DIRECTIVE_in_field1397;
//    public static final BitSet FOLLOW_METHOD_DIRECTIVE_in_method1508;
//    public static final BitSet FOLLOW_access_list_in_method1510;
//    public static final BitSet FOLLOW_member_name_in_method1512;
//    public static final BitSet FOLLOW_method_prototype_in_method1514;
//    public static final BitSet FOLLOW_statements_and_directives_in_method1516;
//    public static final BitSet FOLLOW_END_METHOD_DIRECTIVE_in_method1522;
//    public static final BitSet FOLLOW_ordered_method_item_in_statements_and_directives1567;
//    public static final BitSet FOLLOW_registers_directive_in_statements_and_directives1575;
//    public static final BitSet FOLLOW_catch_directive_in_statements_and_directives1583;
//    public static final BitSet FOLLOW_catchall_directive_in_statements_and_directives1591;
//    public static final BitSet FOLLOW_parameter_directive_in_statements_and_directives1599;
//    public static final BitSet FOLLOW_annotation_in_statements_and_directives1607;
//    public static final BitSet FOLLOW_label_in_ordered_method_item1692;
//    public static final BitSet FOLLOW_instruction_in_ordered_method_item1698;
//    public static final BitSet FOLLOW_debug_directive_in_ordered_method_item1704;
//    public static final BitSet FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1724;
//    public static final BitSet FOLLOW_integral_literal_in_registers_directive1728;
//    public static final BitSet FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1748;
//    public static final BitSet FOLLOW_integral_literal_in_registers_directive1752;
//    public static final BitSet FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id1784;
//    public static final BitSet FOLLOW_SIMPLE_NAME_in_simple_name1797;
//    public static final BitSet FOLLOW_ACCESS_SPEC_in_simple_name1803;
//    public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1814;
//    public static final BitSet FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1825;
//    public static final BitSet FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1836;
//    public static final BitSet FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1847;
//    public static final BitSet FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1858;
//    public static final BitSet FOLLOW_BOOL_LITERAL_in_simple_name1869;
//    public static final BitSet FOLLOW_NULL_LITERAL_in_simple_name1880;
//    public static final BitSet FOLLOW_REGISTER_in_simple_name1891;
//    public static final BitSet FOLLOW_param_list_or_id_in_simple_name1902;
//    public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_simple_name1912;
//    public static final BitSet FOLLOW_VOID_TYPE_in_simple_name1923;
//    public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_simple_name1934;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name1945;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name1956;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name1967;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name1978;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name1989;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2000;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2011;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2022;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2033;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_simple_name2044;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_simple_name2055;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2066;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2077;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2088;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2099;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_simple_name2110;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2121;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2132;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2143;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2154;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT25x_in_simple_name2165;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2176;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2187;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2198;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2209;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2220;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2231;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2242;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2253;
//    public static final BitSet FOLLOW_simple_name_in_member_name2268;
//    public static final BitSet FOLLOW_MEMBER_NAME_in_member_name2274;
//    public static final BitSet FOLLOW_OPEN_PAREN_in_method_prototype2289;
//    public static final BitSet FOLLOW_param_list_in_method_prototype2291;
//    public static final BitSet FOLLOW_CLOSE_PAREN_in_method_prototype2293;
//    public static final BitSet FOLLOW_type_descriptor_in_method_prototype2295;
//    public static final BitSet FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id_primitive_type2325;
//    public static final BitSet FOLLOW_param_list_or_id_primitive_type_in_param_list2340;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_param_list2347;
//    public static final BitSet FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor2358;
//    public static final BitSet FOLLOW_set_in_array_descriptor2360;
//    public static final BitSet FOLLOW_VOID_TYPE_in_type_descriptor2376;
//    public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_type_descriptor2382;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_type_descriptor2388;
//    public static final BitSet FOLLOW_array_descriptor_in_type_descriptor2394;
//    public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor2404;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor2410;
//    public static final BitSet FOLLOW_array_descriptor_in_nonvoid_type_descriptor2416;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor2426;
//    public static final BitSet FOLLOW_array_descriptor_in_reference_type_descriptor2432;
//    public static final BitSet FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2442;
//    public static final BitSet FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2453;
//    public static final BitSet FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2468;
//    public static final BitSet FOLLOW_FLOAT_LITERAL_in_float_literal2479;
//    public static final BitSet FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2489;
//    public static final BitSet FOLLOW_DOUBLE_LITERAL_in_double_literal2500;
//    public static final BitSet FOLLOW_LONG_LITERAL_in_literal2510;
//    public static final BitSet FOLLOW_integer_literal_in_literal2516;
//    public static final BitSet FOLLOW_SHORT_LITERAL_in_literal2522;
//    public static final BitSet FOLLOW_BYTE_LITERAL_in_literal2528;
//    public static final BitSet FOLLOW_float_literal_in_literal2534;
//    public static final BitSet FOLLOW_double_literal_in_literal2540;
//    public static final BitSet FOLLOW_CHAR_LITERAL_in_literal2546;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_literal2552;
//    public static final BitSet FOLLOW_BOOL_LITERAL_in_literal2558;
//    public static final BitSet FOLLOW_NULL_LITERAL_in_literal2564;
//    public static final BitSet FOLLOW_array_literal_in_literal2570;
//    public static final BitSet FOLLOW_subannotation_in_literal2576;
//    public static final BitSet FOLLOW_type_field_method_literal_in_literal2582;
//    public static final BitSet FOLLOW_enum_literal_in_literal2588;
//    public static final BitSet FOLLOW_integer_literal_in_parsed_integer_literal2601;
//    public static final BitSet FOLLOW_LONG_LITERAL_in_integral_literal2613;
//    public static final BitSet FOLLOW_integer_literal_in_integral_literal2619;
//    public static final BitSet FOLLOW_SHORT_LITERAL_in_integral_literal2625;
//    public static final BitSet FOLLOW_CHAR_LITERAL_in_integral_literal2631;
//    public static final BitSet FOLLOW_BYTE_LITERAL_in_integral_literal2637;
//    public static final BitSet FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2647;
//    public static final BitSet FOLLOW_integer_literal_in_fixed_32bit_literal2653;
//    public static final BitSet FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2659;
//    public static final BitSet FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2665;
//    public static final BitSet FOLLOW_float_literal_in_fixed_32bit_literal2671;
//    public static final BitSet FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2677;
//    public static final BitSet FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2683;
//    public static final BitSet FOLLOW_integer_literal_in_fixed_literal2693;
//    public static final BitSet FOLLOW_LONG_LITERAL_in_fixed_literal2699;
//    public static final BitSet FOLLOW_SHORT_LITERAL_in_fixed_literal2705;
//    public static final BitSet FOLLOW_BYTE_LITERAL_in_fixed_literal2711;
//    public static final BitSet FOLLOW_float_literal_in_fixed_literal2717;
//    public static final BitSet FOLLOW_double_literal_in_fixed_literal2723;
//    public static final BitSet FOLLOW_CHAR_LITERAL_in_fixed_literal2729;
//    public static final BitSet FOLLOW_BOOL_LITERAL_in_fixed_literal2735;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_array_literal2745;
//    public static final BitSet FOLLOW_literal_in_array_literal2748;
//    public static final BitSet FOLLOW_COMMA_in_array_literal2751;
//    public static final BitSet FOLLOW_literal_in_array_literal2753;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_array_literal2761;
//    public static final BitSet FOLLOW_simple_name_in_annotation_element2785;
//    public static final BitSet FOLLOW_EQUAL_in_annotation_element2787;
//    public static final BitSet FOLLOW_literal_in_annotation_element2789;
//    public static final BitSet FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2814;
//    public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_annotation2816;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_annotation2818;
//    public static final BitSet FOLLOW_annotation_element_in_annotation2824;
//    public static final BitSet FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2827;
//    public static final BitSet FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation2860;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_subannotation2862;
//    public static final BitSet FOLLOW_annotation_element_in_subannotation2864;
//    public static final BitSet FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation2867;
//    public static final BitSet FOLLOW_ENUM_DIRECTIVE_in_enum_literal2894;
//    public static final BitSet FOLLOW_field_reference_in_enum_literal2896;
//    public static final BitSet FOLLOW_reference_type_descriptor_in_type_field_method_literal2916;
//    public static final BitSet FOLLOW_reference_type_descriptor_in_type_field_method_literal2925;
//    public static final BitSet FOLLOW_ARROW_in_type_field_method_literal2927;
//    public static final BitSet FOLLOW_member_name_in_type_field_method_literal2939;
//    public static final BitSet FOLLOW_COLON_in_type_field_method_literal2941;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal2943;
//    public static final BitSet FOLLOW_member_name_in_type_field_method_literal2966;
//    public static final BitSet FOLLOW_method_prototype_in_type_field_method_literal2968;
//    public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3001;
//    public static final BitSet FOLLOW_VOID_TYPE_in_type_field_method_literal3007;
//    public static final BitSet FOLLOW_reference_type_descriptor_in_method_reference3018;
//    public static final BitSet FOLLOW_ARROW_in_method_reference3020;
//    public static final BitSet FOLLOW_member_name_in_method_reference3024;
//    public static final BitSet FOLLOW_method_prototype_in_method_reference3026;
//    public static final BitSet FOLLOW_reference_type_descriptor_in_field_reference3048;
//    public static final BitSet FOLLOW_ARROW_in_field_reference3050;
//    public static final BitSet FOLLOW_member_name_in_field_reference3054;
//    public static final BitSet FOLLOW_COLON_in_field_reference3056;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field_reference3058;
//    public static final BitSet FOLLOW_COLON_in_label3079;
//    public static final BitSet FOLLOW_simple_name_in_label3081;
//    public static final BitSet FOLLOW_COLON_in_label_ref3100;
//    public static final BitSet FOLLOW_simple_name_in_label_ref3102;
//    public static final BitSet FOLLOW_REGISTER_in_register_list3116;
//    public static final BitSet FOLLOW_COMMA_in_register_list3119;
//    public static final BitSet FOLLOW_REGISTER_in_register_list3121;
//    public static final BitSet FOLLOW_REGISTER_in_register_range3156;
//    public static final BitSet FOLLOW_DOTDOT_in_register_range3159;
//    public static final BitSet FOLLOW_REGISTER_in_register_range3163;
//    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3192;
//    public static final BitSet FOLLOW_field_reference_in_verification_error_reference3196;
//    public static final BitSet FOLLOW_method_reference_in_verification_error_reference3200;
//    public static final BitSet FOLLOW_CATCH_DIRECTIVE_in_catch_directive3210;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_catch_directive3212;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_catch_directive3214;
//    public static final BitSet FOLLOW_label_ref_in_catch_directive3218;
//    public static final BitSet FOLLOW_DOTDOT_in_catch_directive3220;
//    public static final BitSet FOLLOW_label_ref_in_catch_directive3224;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_catch_directive3226;
//    public static final BitSet FOLLOW_label_ref_in_catch_directive3230;
//    public static final BitSet FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3262;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_catchall_directive3264;
//    public static final BitSet FOLLOW_label_ref_in_catchall_directive3268;
//    public static final BitSet FOLLOW_DOTDOT_in_catchall_directive3270;
//    public static final BitSet FOLLOW_label_ref_in_catchall_directive3274;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_catchall_directive3276;
//    public static final BitSet FOLLOW_label_ref_in_catchall_directive3280;
//    public static final BitSet FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3319;
//    public static final BitSet FOLLOW_REGISTER_in_parameter_directive3321;
//    public static final BitSet FOLLOW_COMMA_in_parameter_directive3324;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_parameter_directive3326;
//    public static final BitSet FOLLOW_annotation_in_parameter_directive3337;
//    public static final BitSet FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3350;
//    public static final BitSet FOLLOW_line_directive_in_debug_directive3423;
//    public static final BitSet FOLLOW_local_directive_in_debug_directive3429;
//    public static final BitSet FOLLOW_end_local_directive_in_debug_directive3435;
//    public static final BitSet FOLLOW_restart_local_directive_in_debug_directive3441;
//    public static final BitSet FOLLOW_prologue_directive_in_debug_directive3447;
//    public static final BitSet FOLLOW_epilogue_directive_in_debug_directive3453;
//    public static final BitSet FOLLOW_source_directive_in_debug_directive3459;
//    public static final BitSet FOLLOW_LINE_DIRECTIVE_in_line_directive3469;
//    public static final BitSet FOLLOW_integral_literal_in_line_directive3471;
//    public static final BitSet FOLLOW_LOCAL_DIRECTIVE_in_local_directive3494;
//    public static final BitSet FOLLOW_REGISTER_in_local_directive3496;
//    public static final BitSet FOLLOW_COMMA_in_local_directive3499;
//    public static final BitSet FOLLOW_NULL_LITERAL_in_local_directive3502;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_local_directive3508;
//    public static final BitSet FOLLOW_COLON_in_local_directive3511;
//    public static final BitSet FOLLOW_VOID_TYPE_in_local_directive3514;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_local_directive3518;
//    public static final BitSet FOLLOW_COMMA_in_local_directive3552;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_local_directive3556;
//    public static final BitSet FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3598;
//    public static final BitSet FOLLOW_REGISTER_in_end_local_directive3600;
//    public static final BitSet FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3623;
//    public static final BitSet FOLLOW_REGISTER_in_restart_local_directive3625;
//    public static final BitSet FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3648;
//    public static final BitSet FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3669;
//    public static final BitSet FOLLOW_SOURCE_DIRECTIVE_in_source_directive3690;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_source_directive3692;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x3717;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x3723;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s3738;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s3744;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i3759;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i3765;
//    public static final BitSet FOLLOW_insn_format10t_in_instruction3782;
//    public static final BitSet FOLLOW_insn_format10x_in_instruction3788;
//    public static final BitSet FOLLOW_insn_format10x_odex_in_instruction3794;
//    public static final BitSet FOLLOW_insn_format11n_in_instruction3800;
//    public static final BitSet FOLLOW_insn_format11x_in_instruction3806;
//    public static final BitSet FOLLOW_insn_format12x_in_instruction3812;
//    public static final BitSet FOLLOW_insn_format20bc_in_instruction3818;
//    public static final BitSet FOLLOW_insn_format20t_in_instruction3824;
//    public static final BitSet FOLLOW_insn_format21c_field_in_instruction3830;
//    public static final BitSet FOLLOW_insn_format21c_field_odex_in_instruction3836;
//    public static final BitSet FOLLOW_insn_format21c_string_in_instruction3842;
//    public static final BitSet FOLLOW_insn_format21c_type_in_instruction3848;
//    public static final BitSet FOLLOW_insn_format21c_lambda_in_instruction3854;
//    public static final BitSet FOLLOW_insn_format21c_method_in_instruction3860;
//    public static final BitSet FOLLOW_insn_format21ih_in_instruction3866;
//    public static final BitSet FOLLOW_insn_format21lh_in_instruction3872;
//    public static final BitSet FOLLOW_insn_format21s_in_instruction3878;
//    public static final BitSet FOLLOW_insn_format21t_in_instruction3884;
//    public static final BitSet FOLLOW_insn_format22b_in_instruction3890;
//    public static final BitSet FOLLOW_insn_format22c_field_in_instruction3896;
//    public static final BitSet FOLLOW_insn_format22c_field_odex_in_instruction3902;
//    public static final BitSet FOLLOW_insn_format22c_type_in_instruction3908;
//    public static final BitSet FOLLOW_insn_format22c_string_in_instruction3914;
//    public static final BitSet FOLLOW_insn_format22cs_field_in_instruction3920;
//    public static final BitSet FOLLOW_insn_format22s_in_instruction3926;
//    public static final BitSet FOLLOW_insn_format22t_in_instruction3932;
//    public static final BitSet FOLLOW_insn_format22x_in_instruction3938;
//    public static final BitSet FOLLOW_insn_format23x_in_instruction3944;
//    public static final BitSet FOLLOW_insn_format25x_in_instruction3950;
//    public static final BitSet FOLLOW_insn_format30t_in_instruction3956;
//    public static final BitSet FOLLOW_insn_format31c_in_instruction3962;
//    public static final BitSet FOLLOW_insn_format31i_in_instruction3968;
//    public static final BitSet FOLLOW_insn_format31t_in_instruction3974;
//    public static final BitSet FOLLOW_insn_format32x_in_instruction3980;
//    public static final BitSet FOLLOW_insn_format35c_method_in_instruction3986;
//    public static final BitSet FOLLOW_insn_format35c_type_in_instruction3992;
//    public static final BitSet FOLLOW_insn_format35c_method_odex_in_instruction3998;
//    public static final BitSet FOLLOW_insn_format35mi_method_in_instruction4004;
//    public static final BitSet FOLLOW_insn_format35ms_method_in_instruction4010;
//    public static final BitSet FOLLOW_insn_format3rc_method_in_instruction4016;
//    public static final BitSet FOLLOW_insn_format3rc_method_odex_in_instruction4022;
//    public static final BitSet FOLLOW_insn_format3rc_type_in_instruction4028;
//    public static final BitSet FOLLOW_insn_format3rmi_method_in_instruction4034;
//    public static final BitSet FOLLOW_insn_format3rms_method_in_instruction4040;
//    public static final BitSet FOLLOW_insn_format51l_in_instruction4046;
//    public static final BitSet FOLLOW_insn_array_data_directive_in_instruction4052;
//    public static final BitSet FOLLOW_insn_packed_switch_directive_in_instruction4058;
//    public static final BitSet FOLLOW_insn_sparse_switch_directive_in_instruction4064;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4084;
//    public static final BitSet FOLLOW_label_ref_in_insn_format10t4086;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4116;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4144;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4165;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format11n4167;
//    public static final BitSet FOLLOW_COMMA_in_insn_format11n4169;
//    public static final BitSet FOLLOW_integral_literal_in_insn_format11n4171;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4203;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format11x4205;
//    public static final BitSet FOLLOW_instruction_format12x_in_insn_format12x4235;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format12x4237;
//    public static final BitSet FOLLOW_COMMA_in_insn_format12x4239;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format12x4241;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4273;
//    public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4275;
//    public static final BitSet FOLLOW_COMMA_in_insn_format20bc4277;
//    public static final BitSet FOLLOW_verification_error_reference_in_insn_format20bc4279;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4316;
//    public static final BitSet FOLLOW_label_ref_in_insn_format20t4318;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4348;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field4350;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21c_field4352;
//    public static final BitSet FOLLOW_field_reference_in_insn_format21c_field4354;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4386;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field_odex4388;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21c_field_odex4390;
//    public static final BitSet FOLLOW_field_reference_in_insn_format21c_field_odex4392;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4430;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21c_string4432;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21c_string4434;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format21c_string4436;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4468;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21c_type4470;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21c_type4472;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4474;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda4506;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21c_lambda4508;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21c_lambda4510;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format21c_lambda4512;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method4544;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method4546;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21c_method4548;
//    public static final BitSet FOLLOW_method_reference_in_insn_format21c_method4550;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4582;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21ih4584;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21ih4586;
//    public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21ih4588;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4620;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21lh4622;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21lh4624;
//    public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21lh4626;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s4658;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21s4660;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21s4662;
//    public static final BitSet FOLLOW_integral_literal_in_insn_format21s4664;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t4696;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format21t4698;
//    public static final BitSet FOLLOW_COMMA_in_insn_format21t4700;
//    public static final BitSet FOLLOW_label_ref_in_insn_format21t4702;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b4734;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22b4736;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22b4738;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22b4740;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22b4742;
//    public static final BitSet FOLLOW_integral_literal_in_insn_format22b4744;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field4778;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field4780;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_field4782;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field4784;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_field4786;
//    public static final BitSet FOLLOW_field_reference_in_insn_format22c_field4788;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex4822;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field_odex4824;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_field_odex4826;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field_odex4828;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_field_odex4830;
//    public static final BitSet FOLLOW_field_reference_in_insn_format22c_field_odex4832;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type4872;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type4874;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_type4876;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type4878;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_type4880;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type4882;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string4916;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_string4918;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_string4920;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22c_string4922;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22c_string4924;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format22c_string4926;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field4960;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22cs_field4962;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22cs_field4964;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22cs_field4966;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22cs_field4968;
//    public static final BitSet FOLLOW_FIELD_OFFSET_in_insn_format22cs_field4970;
//    public static final BitSet FOLLOW_instruction_format22s_in_insn_format22s4991;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22s4993;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22s4995;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22s4997;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22s4999;
//    public static final BitSet FOLLOW_integral_literal_in_insn_format22s5001;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5035;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22t5037;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22t5039;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22t5041;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22t5043;
//    public static final BitSet FOLLOW_label_ref_in_insn_format22t5045;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5079;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22x5081;
//    public static final BitSet FOLLOW_COMMA_in_insn_format22x5083;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format22x5085;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5117;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format23x5119;
//    public static final BitSet FOLLOW_COMMA_in_insn_format23x5121;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format23x5123;
//    public static final BitSet FOLLOW_COMMA_in_insn_format23x5125;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format23x5127;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x5161;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format25x5163;
//    public static final BitSet FOLLOW_COMMA_in_insn_format25x5165;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format25x5167;
//    public static final BitSet FOLLOW_register_list_in_insn_format25x5169;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format25x5171;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5203;
//    public static final BitSet FOLLOW_label_ref_in_insn_format30t5205;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5235;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format31c5237;
//    public static final BitSet FOLLOW_COMMA_in_insn_format31c5239;
//    public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format31c5241;
//    public static final BitSet FOLLOW_instruction_format31i_in_insn_format31i5272;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format31i5274;
//    public static final BitSet FOLLOW_COMMA_in_insn_format31i5276;
//    public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format31i5278;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5310;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format31t5312;
//    public static final BitSet FOLLOW_COMMA_in_insn_format31t5314;
//    public static final BitSet FOLLOW_label_ref_in_insn_format31t5316;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5348;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format32x5350;
//    public static final BitSet FOLLOW_COMMA_in_insn_format32x5352;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format32x5354;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method5386;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_method5388;
//    public static final BitSet FOLLOW_register_list_in_insn_format35c_method5390;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_method5392;
//    public static final BitSet FOLLOW_COMMA_in_insn_format35c_method5394;
//    public static final BitSet FOLLOW_method_reference_in_insn_format35c_method5396;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5428;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_type5430;
//    public static final BitSet FOLLOW_register_list_in_insn_format35c_type5432;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_type5434;
//    public static final BitSet FOLLOW_COMMA_in_insn_format35c_type5436;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5438;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5470;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5472;
//    public static final BitSet FOLLOW_register_list_in_insn_format35c_method_odex5474;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5476;
//    public static final BitSet FOLLOW_COMMA_in_insn_format35c_method_odex5478;
//    public static final BitSet FOLLOW_method_reference_in_insn_format35c_method_odex5480;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5501;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35mi_method5503;
//    public static final BitSet FOLLOW_register_list_in_insn_format35mi_method5505;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5507;
//    public static final BitSet FOLLOW_COMMA_in_insn_format35mi_method5509;
//    public static final BitSet FOLLOW_INLINE_INDEX_in_insn_format35mi_method5511;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5532;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35ms_method5534;
//    public static final BitSet FOLLOW_register_list_in_insn_format35ms_method5536;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5538;
//    public static final BitSet FOLLOW_COMMA_in_insn_format35ms_method5540;
//    public static final BitSet FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5542;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5563;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_method5565;
//    public static final BitSet FOLLOW_register_range_in_insn_format3rc_method5567;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5569;
//    public static final BitSet FOLLOW_COMMA_in_insn_format3rc_method5571;
//    public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method5573;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5605;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5607;
//    public static final BitSet FOLLOW_register_list_in_insn_format3rc_method_odex5609;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5611;
//    public static final BitSet FOLLOW_COMMA_in_insn_format3rc_method_odex5613;
//    public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method_odex5615;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5636;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_type5638;
//    public static final BitSet FOLLOW_register_range_in_insn_format3rc_type5640;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5642;
//    public static final BitSet FOLLOW_COMMA_in_insn_format3rc_type5644;
//    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5646;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method5678;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rmi_method5680;
//    public static final BitSet FOLLOW_register_range_in_insn_format3rmi_method5682;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method5684;
//    public static final BitSet FOLLOW_COMMA_in_insn_format3rmi_method5686;
//    public static final BitSet FOLLOW_INLINE_INDEX_in_insn_format3rmi_method5688;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method5709;
//    public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rms_method5711;
//    public static final BitSet FOLLOW_register_range_in_insn_format3rms_method5713;
//    public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rms_method5715;
//    public static final BitSet FOLLOW_COMMA_in_insn_format3rms_method5717;
//    public static final BitSet FOLLOW_VTABLE_INDEX_in_insn_format3rms_method5719;
//    public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l5740;
//    public static final BitSet FOLLOW_REGISTER_in_insn_format51l5742;
//    public static final BitSet FOLLOW_COMMA_in_insn_format51l5744;
//    public static final BitSet FOLLOW_fixed_literal_in_insn_format51l5746;
//    public static final BitSet FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5773;
//    public static final BitSet FOLLOW_parsed_integer_literal_in_insn_array_data_directive5779;
//    public static final BitSet FOLLOW_fixed_literal_in_insn_array_data_directive5791;
//    public static final BitSet FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5794;
//    public static final BitSet FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5840;
//    public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive5846;
//    public static final BitSet FOLLOW_label_ref_in_insn_packed_switch_directive5852;
//    public static final BitSet FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5859;
//    public static final BitSet FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5933;
//    public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive5940;
//    public static final BitSet FOLLOW_ARROW_in_insn_sparse_switch_directive5942;
//    public static final BitSet FOLLOW_label_ref_in_insn_sparse_switch_directive5944;
//    public static final BitSet FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5952;
//
//    public smaliParser(TokenStream input) {
//        this(input, new RecognizerSharedState());
//    }
//
//    public smaliParser(TokenStream input, RecognizerSharedState state) {
//        super(input, state);
//        this.adaptor = new CommonTreeAdaptor();
//        this.verboseErrors = false;
//        this.allowOdex = false;
//        this.apiLevel = 15;
//        this.opcodes = Opcodes.forApi(this.apiLevel);
//        this.smali_file_stack = new Stack();
//        this.statements_and_directives_stack = new Stack();
//        this.dfa30 = new smaliParser.DFA30(this);
//        this.dfa38 = new smaliParser.DFA38(this);
//        this.dfa40 = new smaliParser.DFA40(this);
//    }
//
//    public String[] getTokenNames() {
//        return tokenNames;
//    }
//
//    public String getGrammarFileName() {
//        return "smaliParser.g";
//    }
//
//    public void setVerboseErrors(boolean verboseErrors) {
//        this.verboseErrors = verboseErrors;
//    }
//
//    public String getErrorMessage(RecognitionException e, String[] tokenNames) {
//        if (this.verboseErrors) {
//            List stack = getRuleInvocationStack(e, this.getClass().getName());
//            String msg = null;
//            if (e instanceof NoViableAltException) {
//                NoViableAltException nvae = (NoViableAltException)e;
//                msg = " no viable alt; token=" + this.getTokenErrorDisplay(e.token) + " (decision=" + nvae.decisionNumber + " state " + nvae.stateNumber + ")" + " decision=<<" + nvae.grammarDecisionDescription + ">>";
//            } else {
//                msg = super.getErrorMessage(e, tokenNames);
//            }
//
//            return stack + " " + msg;
//        } else {
//            return super.getErrorMessage(e, tokenNames);
//        }
//    }
//
//    public String getTokenErrorDisplay(Token t) {
//        System.out.println("t=" + t.getClass());
//        if (!this.verboseErrors) {
//            String s = t.getText();
//            if (s == null) {
//                if (t.getType() == -1) {
//                    s = "<EOF>";
//                } else {
//                    s = "<" + tokenNames[t.getType()] + ">";
//                }
//            }
//
//            s = s.replaceAll("\n", "\\\\n");
//            s = s.replaceAll("\r", "\\\\r");
//            s = s.replaceAll("\t", "\\\\t");
//            return "'" + s + "'";
//        } else {
//            CommonToken ct = (CommonToken)t;
//            String channelStr = "";
//            if (t.getChannel() > 0) {
//                channelStr = ",channel=" + t.getChannel();
//            }
//
//            String txt = t.getText();
//            if (txt != null) {
//                txt = txt.replaceAll("\n", "\\\\n");
//                txt = txt.replaceAll("\r", "\\\\r");
//                txt = txt.replaceAll("\t", "\\\\t");
//            } else {
//                txt = "<no text>";
//            }
//
//            System.out.println("type=" + t.getType() + " len=" + tokenNames.length);
//            return "[@" + t.getTokenIndex() + "," + ct.getStartIndex() + ":" + ct.getStopIndex() + "='" + txt + "',<" + tokenNames[t.getType()] + ">" + channelStr + "," + t.getLine() + ":" + t.getCharPositionInLine() + "]";
//        }
//    }
//
//    public String getErrorHeader(RecognitionException e) {
//        return this.getSourceName() + "[" + e.line + "," + e.charPositionInLine + "]";
//    }
//
//    private CommonTree buildTree(int type, String text, List<CommonTree> children) {
//        CommonTree root = new CommonTree(new CommonToken(type, text));
//        Iterator var5 = children.iterator();
//
//        while(var5.hasNext()) {
//            CommonTree child = (CommonTree)var5.next();
//            root.addChild(child);
//        }
//
//        return root;
//    }
//
//    private void throwOdexedInstructionException(IntStream input, String odexedInstruction) throws OdexedInstructionException {
//        throw new OdexedInstructionException(input, odexedInstruction);
//    }
//
//    public final smaliParser.smali_file_return smali_file() throws RecognitionException {
//        this.smali_file_stack.push(new smaliParser.smali_file_scope());
//        smaliParser.smali_file_return retval = new smaliParser.smali_file_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token EOF8 = null;
//        ParserRuleReturnScope class_spec1 = null;
//        ParserRuleReturnScope super_spec2 = null;
//        ParserRuleReturnScope implements_spec3 = null;
//        ParserRuleReturnScope source_spec4 = null;
//        ParserRuleReturnScope method5 = null;
//        ParserRuleReturnScope field6 = null;
//        ParserRuleReturnScope annotation7 = null;
//        CommonTree EOF8_tree = null;
//        RewriteRuleTokenStream stream_EOF = new RewriteRuleTokenStream(this.adaptor, "token EOF");
//        RewriteRuleSubtreeStream stream_class_spec = new RewriteRuleSubtreeStream(this.adaptor, "rule class_spec");
//        RewriteRuleSubtreeStream stream_annotation = new RewriteRuleSubtreeStream(this.adaptor, "rule annotation");
//        RewriteRuleSubtreeStream stream_method = new RewriteRuleSubtreeStream(this.adaptor, "rule method");
//        RewriteRuleSubtreeStream stream_field = new RewriteRuleSubtreeStream(this.adaptor, "rule field");
//        RewriteRuleSubtreeStream stream_super_spec = new RewriteRuleSubtreeStream(this.adaptor, "rule super_spec");
//        RewriteRuleSubtreeStream stream_implements_spec = new RewriteRuleSubtreeStream(this.adaptor, "rule implements_spec");
//        RewriteRuleSubtreeStream stream_source_spec = new RewriteRuleSubtreeStream(this.adaptor, "rule source_spec");
//        ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasClassSpec = ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSuperSpec = ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSourceSpec = false;
//        ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).classAnnotations = new ArrayList();
//
//        try {
//            int cnt1 = 0;
//
//            while(true) {
//                int alt1 = 8;
//                int LA1_0 = this.input.LA(1);
//                System.out.println("LA1_0=" + LA1_0);
//                if (LA1_0 == 16 && !((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasClassSpec) {
//                    alt1 = 1;
//                } else if (LA1_0 == 200 && !((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSuperSpec) {
//                    alt1 = 2;
//                } else if (LA1_0 == 40) {
//                    alt1 = 3;
//                } else if (LA1_0 == 196 && !((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSourceSpec) {
//                    alt1 = 4;
//                } else if (LA1_0 == 180) {
//                    alt1 = 5;
//                } else if (LA1_0 == 36) {
//                    alt1 = 6;
//                } else if (LA1_0 == 5) {
//                    alt1 = 7;
//                }
//
//                System.out.println("alt1=" + alt1);
//                switch(alt1) {
//                    case 1:
//                        if (((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasClassSpec) {
//                            throw new FailedPredicateException(this.input, "smali_file", "!$smali_file::hasClassSpec");
//                        }
//
//                        this.pushFollow(FOLLOW_class_spec_in_smali_file1095);
//                        class_spec1 = this.class_spec();
//                        --this.state._fsp;
//                        stream_class_spec.add(class_spec1.getTree());
//                        ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasClassSpec = true;
//                        break;
//                    case 2:
//                        if (((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSuperSpec) {
//                            throw new FailedPredicateException(this.input, "smali_file", "!$smali_file::hasSuperSpec");
//                        }
//
//                        this.pushFollow(FOLLOW_super_spec_in_smali_file1106);
//                        super_spec2 = this.super_spec();
//                        --this.state._fsp;
//                        stream_super_spec.add(super_spec2.getTree());
//                        ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSuperSpec = true;
//                        break;
//                    case 3:
//                        this.pushFollow(FOLLOW_implements_spec_in_smali_file1114);
//                        implements_spec3 = this.implements_spec();
//                        --this.state._fsp;
//                        stream_implements_spec.add(implements_spec3.getTree());
//                        break;
//                    case 4:
//                        if (((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSourceSpec) {
//                            throw new FailedPredicateException(this.input, "smali_file", "!$smali_file::hasSourceSpec");
//                        }
//
//                        this.pushFollow(FOLLOW_source_spec_in_smali_file1123);
//                        source_spec4 = this.source_spec();
//                        --this.state._fsp;
//                        stream_source_spec.add(source_spec4.getTree());
//                        ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSourceSpec = true;
//                        break;
//                    case 5:
//                        this.pushFollow(FOLLOW_method_in_smali_file1131);
//                        method5 = this.method();
//                        --this.state._fsp;
//                        stream_method.add(method5.getTree());
//                        break;
//                    case 6:
//                        this.pushFollow(FOLLOW_field_in_smali_file1137);
//                        field6 = this.field();
//                        --this.state._fsp;
//                        stream_field.add(field6.getTree());
//                        break;
//                    case 7:
//                        this.pushFollow(FOLLOW_annotation_in_smali_file1143);
//                        annotation7 = this.annotation();
//                        --this.state._fsp;
//                        stream_annotation.add(annotation7.getTree());
//                        ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).classAnnotations.add(annotation7 != null ? (CommonTree)annotation7.getTree() : null);
//                        break;
//                    default:
//                        if (cnt1 < 1) {
//                            EarlyExitException eee = new EarlyExitException(1, this.input);
//                            throw eee;
//                        }
//
//                       EOF8 = (Token)this.match(this.input, -1, FOLLOW_EOF_in_smali_file1154);
//                        stream_EOF.add(EOF8);
//                        if (!((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasClassSpec) {
//                            throw new SemanticException(this.input, "The file must contain a .class directive", new Object[0]);
//                        }
//
//                        if (!((smaliParser.smali_file_scope)this.smali_file_stack.peek()).hasSuperSpec && !(class_spec1 != null ? ((smaliParser.class_spec_return)class_spec1).className : null).equals("Ljava/lang/Object;")) {
//                            throw new SemanticException(this.input, "The file must contain a .super directive", new Object[0]);
//                        }
//
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(101, "I_CLASS_DEF"), root_1);
//                        this.adaptor.addChild(root_1, stream_class_spec.nextTree());
//                        if (stream_super_spec.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_super_spec.nextTree());
//                        }
//
//                        stream_super_spec.reset();
//
//                        while(stream_implements_spec.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_implements_spec.nextTree());
//                        }
//
//                        stream_implements_spec.reset();
//                        if (stream_source_spec.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_source_spec.nextTree());
//                        }
//
//                        stream_source_spec.reset();
//                        CommonTree root_2 = (CommonTree)this.adaptor.nil();
//                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(118, "I_METHODS"), root_2);
//
//                        while(stream_method.hasNext()) {
//                            this.adaptor.addChild(root_2, stream_method.nextTree());
//                        }
//
//                        stream_method.reset();
//                        this.adaptor.addChild(root_1, root_2);
//                        root_2 = (CommonTree)this.adaptor.nil();
//                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(109, "I_FIELDS"), root_2);
//
//                        while(stream_field.hasNext()) {
//                            this.adaptor.addChild(root_2, stream_field.nextTree());
//                        }
//
//                        stream_field.reset();
//                        this.adaptor.addChild(root_1, root_2);
//                        this.adaptor.addChild(root_1, this.buildTree(94, "I_ANNOTATIONS", ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).classAnnotations));
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//
//                ++cnt1;
//            }
//        } catch (RecognitionException var27) {
//            var27.printStackTrace();
//            this.reportError(var27);
//            this.recover(this.input, var27);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var27);
//        } finally {
//            this.smali_file_stack.pop();
//        }
//
//        return retval;
//    }
//
//    public final smaliParser.class_spec_return class_spec() throws RecognitionException {
//        smaliParser.class_spec_return retval = new smaliParser.class_spec_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token CLASS_DIRECTIVE9 = null;
//        Token CLASS_DESCRIPTOR11 = null;
//        ParserRuleReturnScope access_list10 = null;
//        CommonTree CLASS_DIRECTIVE9_tree = null;
//        CommonTree CLASS_DESCRIPTOR11_tree = null;
//        RewriteRuleTokenStream stream_CLASS_DESCRIPTOR = new RewriteRuleTokenStream(this.adaptor, "token CLASS_DESCRIPTOR");
//        RewriteRuleTokenStream stream_CLASS_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token CLASS_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_access_list = new RewriteRuleSubtreeStream(this.adaptor, "rule access_list");
//
//        try {
//            try {
//                CLASS_DIRECTIVE9 = (Token)this.match(this.input, 16, FOLLOW_CLASS_DIRECTIVE_in_class_spec1241);
//                stream_CLASS_DIRECTIVE.add(CLASS_DIRECTIVE9);
//                this.pushFollow(FOLLOW_access_list_in_class_spec1243);
//                access_list10 = this.access_list();
//                --this.state._fsp;
//                stream_access_list.add(access_list10.getTree());
//                CLASS_DESCRIPTOR11 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_class_spec1245);
//                stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR11);
//                retval.className = CLASS_DESCRIPTOR11 != null ? CLASS_DESCRIPTOR11.getText() : null;
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                this.adaptor.addChild(root_0, stream_CLASS_DESCRIPTOR.nextNode());
//                this.adaptor.addChild(root_0, stream_access_list.nextTree());
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var15) {
//                this.reportError(var15);
//                this.recover(this.input, var15);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var15);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.super_spec_return super_spec() throws RecognitionException {
//        smaliParser.super_spec_return retval = new smaliParser.super_spec_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token SUPER_DIRECTIVE12 = null;
//        Token CLASS_DESCRIPTOR13 = null;
//        CommonTree SUPER_DIRECTIVE12_tree = null;
//        CommonTree CLASS_DESCRIPTOR13_tree = null;
//        RewriteRuleTokenStream stream_CLASS_DESCRIPTOR = new RewriteRuleTokenStream(this.adaptor, "token CLASS_DESCRIPTOR");
//        RewriteRuleTokenStream stream_SUPER_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token SUPER_DIRECTIVE");
//
//        try {
//            try {
//                SUPER_DIRECTIVE12 = (Token)this.match(this.input, 200, FOLLOW_SUPER_DIRECTIVE_in_super_spec1263);
//                stream_SUPER_DIRECTIVE.add(SUPER_DIRECTIVE12);
//                CLASS_DESCRIPTOR13 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_super_spec1265);
//                stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR13);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(173, retval.start, "I_SUPER"), root_1);
//                this.adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.implements_spec_return implements_spec() throws RecognitionException {
//        smaliParser.implements_spec_return retval = new smaliParser.implements_spec_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token IMPLEMENTS_DIRECTIVE14 = null;
//        Token CLASS_DESCRIPTOR15 = null;
//        CommonTree IMPLEMENTS_DIRECTIVE14_tree = null;
//        CommonTree CLASS_DESCRIPTOR15_tree = null;
//        RewriteRuleTokenStream stream_IMPLEMENTS_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token IMPLEMENTS_DIRECTIVE");
//        RewriteRuleTokenStream stream_CLASS_DESCRIPTOR = new RewriteRuleTokenStream(this.adaptor, "token CLASS_DESCRIPTOR");
//
//        try {
//            try {
//                IMPLEMENTS_DIRECTIVE14 = (Token)this.match(this.input, 40, FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1284);
//                stream_IMPLEMENTS_DIRECTIVE.add(IMPLEMENTS_DIRECTIVE14);
//                CLASS_DESCRIPTOR15 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1286);
//                stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR15);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(112, retval.start, "I_IMPLEMENTS"), root_1);
//                this.adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.source_spec_return source_spec() throws RecognitionException {
//        smaliParser.source_spec_return retval = new smaliParser.source_spec_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token SOURCE_DIRECTIVE16 = null;
//        Token STRING_LITERAL17 = null;
//        CommonTree SOURCE_DIRECTIVE16_tree = null;
//        CommonTree STRING_LITERAL17_tree = null;
//        RewriteRuleTokenStream stream_SOURCE_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token SOURCE_DIRECTIVE");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//
//        try {
//            try {
//                SOURCE_DIRECTIVE16 = (Token)this.match(this.input, 196, FOLLOW_SOURCE_DIRECTIVE_in_source_spec1305);
//                stream_SOURCE_DIRECTIVE.add(SOURCE_DIRECTIVE16);
//                STRING_LITERAL17 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_source_spec1307);
//                stream_STRING_LITERAL.add(STRING_LITERAL17);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(132, retval.start, "I_SOURCE"), root_1);
//                this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.access_list_return access_list() throws RecognitionException {
//        smaliParser.access_list_return retval = new smaliParser.access_list_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ACCESS_SPEC18 = null;
//        CommonTree ACCESS_SPEC18_tree = null;
//        RewriteRuleTokenStream stream_ACCESS_SPEC = new RewriteRuleTokenStream(this.adaptor, "token ACCESS_SPEC");
//
//        try {
//            while(true) {
//                int alt2 = 2;
//                int LA2_0 = this.input.LA(1);
//                if (LA2_0 == 4) {
//                    int LA2_2 = this.input.LA(2);
//                    if (LA2_2 == 4 || LA2_2 == 6 || LA2_2 == 10 || LA2_2 == 15 || LA2_2 == 23 || LA2_2 == 39 || LA2_2 >= 42 && LA2_2 <= 44 || LA2_2 == 46 || LA2_2 == 48 || LA2_2 >= 51 && LA2_2 <= 56 || LA2_2 == 60 || LA2_2 >= 62 && LA2_2 <= 66 || LA2_2 >= 68 && LA2_2 <= 69 || LA2_2 >= 71 && LA2_2 <= 72 || LA2_2 >= 76 && LA2_2 <= 77 || LA2_2 >= 79 && LA2_2 <= 83 || LA2_2 == 89 || LA2_2 == 179 || LA2_2 >= 181 && LA2_2 <= 182 || LA2_2 >= 187 && LA2_2 <= 189 || LA2_2 == 191 || LA2_2 == 195 || LA2_2 >= 201 && LA2_2 <= 202) {
//                        alt2 = 1;
//                    }
//                }
//
//                switch(alt2) {
//                    case 1:
//                        ACCESS_SPEC18 = (Token)this.match(this.input, 4, FOLLOW_ACCESS_SPEC_in_access_list1326);
//                        stream_ACCESS_SPEC.add(ACCESS_SPEC18);
//                        break;
//                    default:
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(92, retval.start, "I_ACCESS_LIST"), root_1);
//
//                        while(stream_ACCESS_SPEC.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_ACCESS_SPEC.nextNode());
//                        }
//
//                        stream_ACCESS_SPEC.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var12) {
//            this.reportError(var12);
//            this.recover(this.input, var12);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var12);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.field_return field() throws RecognitionException {
//        smaliParser.field_return retval = new smaliParser.field_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token FIELD_DIRECTIVE19 = null;
//        Token COLON22 = null;
//        Token EQUAL24 = null;
//        Token END_FIELD_DIRECTIVE27 = null;
//        ParserRuleReturnScope access_list20 = null;
//        ParserRuleReturnScope member_name21 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor23 = null;
//        ParserRuleReturnScope literal25 = null;
//        ParserRuleReturnScope annotation26 = null;
//        CommonTree FIELD_DIRECTIVE19_tree = null;
//        CommonTree COLON22_tree = null;
//        CommonTree EQUAL24_tree = null;
//        CommonTree END_FIELD_DIRECTIVE27_tree = null;
//        RewriteRuleTokenStream stream_END_FIELD_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_FIELD_DIRECTIVE");
//        RewriteRuleTokenStream stream_EQUAL = new RewriteRuleTokenStream(this.adaptor, "token EQUAL");
//        RewriteRuleTokenStream stream_FIELD_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token FIELD_DIRECTIVE");
//        RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
//        RewriteRuleSubtreeStream stream_annotation = new RewriteRuleSubtreeStream(this.adaptor, "rule annotation");
//        RewriteRuleSubtreeStream stream_access_list = new RewriteRuleSubtreeStream(this.adaptor, "rule access_list");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//        RewriteRuleSubtreeStream stream_member_name = new RewriteRuleSubtreeStream(this.adaptor, "rule member_name");
//        RewriteRuleSubtreeStream stream_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule literal");
//        ArrayList annotations = new ArrayList();
//
//        try {
//            FIELD_DIRECTIVE19 = (Token)this.match(this.input, 36, FOLLOW_FIELD_DIRECTIVE_in_field1357);
//            stream_FIELD_DIRECTIVE.add(FIELD_DIRECTIVE19);
//            this.pushFollow(FOLLOW_access_list_in_field1359);
//            access_list20 = this.access_list();
//            --this.state._fsp;
//            stream_access_list.add(access_list20.getTree());
//            this.pushFollow(FOLLOW_member_name_in_field1361);
//            member_name21 = this.member_name();
//            --this.state._fsp;
//            stream_member_name.add(member_name21.getTree());
//            COLON22 = (Token)this.match(this.input, 19, FOLLOW_COLON_in_field1363);
//            stream_COLON.add(COLON22);
//            this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_field1365);
//            nonvoid_type_descriptor23 = this.nonvoid_type_descriptor();
//            --this.state._fsp;
//            stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor23.getTree());
//            int alt3 = 2;
//            int LA3_0 = this.input.LA(1);
//            if (LA3_0 == 35) {
//                alt3 = 1;
//            }
//
//            switch(alt3) {
//                case 1:
//                    EQUAL24 = (Token)this.match(this.input, 35, FOLLOW_EQUAL_in_field1368);
//                    stream_EQUAL.add(EQUAL24);
//                    this.pushFollow(FOLLOW_literal_in_field1370);
//                    literal25 = this.literal();
//                    --this.state._fsp;
//                    stream_literal.add(literal25.getTree());
//            }
//
//            while(true) {
//                int alt5 = 2;
//                int LA5_0 = this.input.LA(1);
//                if (LA5_0 == 5) {
//                    int LA4_9 = this.input.LA(2);
//                    if (this.input.LA(1) == 5) {
//                        alt5 = 1;
//                    }
//                }
//
//                switch(alt5) {
//                    case 1:
//                        if (this.input.LA(1) != 5) {
//                            throw new FailedPredicateException(this.input, "field", "input.LA(1) == ANNOTATION_DIRECTIVE");
//                        }
//
//                        this.pushFollow(FOLLOW_annotation_in_field1383);
//                        annotation26 = this.annotation();
//                        --this.state._fsp;
//                        stream_annotation.add(annotation26.getTree());
//                        annotations.add(annotation26 != null ? (CommonTree)annotation26.getTree() : null);
//                        break;
//                    default:
//                        LA5_0 = this.input.LA(1);
//                        if (LA5_0 == 26) {
//                            alt5 = 1;
//                        } else {
//                            if (LA5_0 != -1 && LA5_0 != 5 && LA5_0 != 16 && LA5_0 != 36 && LA5_0 != 40 && LA5_0 != 180 && LA5_0 != 196 && LA5_0 != 200) {
//                                NoViableAltException nvae = new NoViableAltException("", 5, 0, this.input);
//                                throw nvae;
//                            }
//
//                            alt5 = 2;
//                        }
//
//                        CommonTree root_1;
//                        CommonTree root_2;
//                        switch(alt5) {
//                            case 1:
//                                END_FIELD_DIRECTIVE27 = (Token)this.match(this.input, 26, FOLLOW_END_FIELD_DIRECTIVE_in_field1397);
//                                stream_END_FIELD_DIRECTIVE.add(END_FIELD_DIRECTIVE27);
//                                retval.tree = root_0;
//                                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                root_0 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(108, retval.start, "I_FIELD"), root_1);
//                                this.adaptor.addChild(root_1, stream_member_name.nextTree());
//                                this.adaptor.addChild(root_1, stream_access_list.nextTree());
//                                root_2 = (CommonTree)this.adaptor.nil();
//                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(111, "I_FIELD_TYPE"), root_2);
//                                this.adaptor.addChild(root_2, stream_nonvoid_type_descriptor.nextTree());
//                                this.adaptor.addChild(root_1, root_2);
//                                if (stream_literal.hasNext()) {
//                                    root_2 = (CommonTree)this.adaptor.nil();
//                                    root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(110, "I_FIELD_INITIAL_VALUE"), root_2);
//                                    this.adaptor.addChild(root_2, stream_literal.nextTree());
//                                    this.adaptor.addChild(root_1, root_2);
//                                }
//
//                                stream_literal.reset();
//                                root_2 = (CommonTree)this.adaptor.nil();
//                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(94, "I_ANNOTATIONS"), root_2);
//
//                                while(stream_annotation.hasNext()) {
//                                    this.adaptor.addChild(root_2, stream_annotation.nextTree());
//                                }
//
//                                stream_annotation.reset();
//                                this.adaptor.addChild(root_1, root_2);
//                                this.adaptor.addChild(root_0, root_1);
//                                retval.tree = root_0;
//                                break;
//                            case 2:
//                                ((smaliParser.smali_file_scope)this.smali_file_stack.peek()).classAnnotations.addAll(annotations);
//                                retval.tree = root_0;
//                                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                root_0 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(108, retval.start, "I_FIELD"), root_1);
//                                this.adaptor.addChild(root_1, stream_member_name.nextTree());
//                                this.adaptor.addChild(root_1, stream_access_list.nextTree());
//                                root_2 = (CommonTree)this.adaptor.nil();
//                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(111, "I_FIELD_TYPE"), root_2);
//                                this.adaptor.addChild(root_2, stream_nonvoid_type_descriptor.nextTree());
//                                this.adaptor.addChild(root_1, root_2);
//                                if (stream_literal.hasNext()) {
//                                    root_2 = (CommonTree)this.adaptor.nil();
//                                    root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(110, "I_FIELD_INITIAL_VALUE"), root_2);
//                                    this.adaptor.addChild(root_2, stream_literal.nextTree());
//                                    this.adaptor.addChild(root_1, root_2);
//                                }
//
//                                stream_literal.reset();
//                                root_2 = (CommonTree)this.adaptor.nil();
//                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(94, "I_ANNOTATIONS"), root_2);
//                                this.adaptor.addChild(root_1, root_2);
//                                this.adaptor.addChild(root_0, root_1);
//                                retval.tree = root_0;
//                        }
//
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var36) {
//            this.reportError(var36);
//            this.recover(this.input, var36);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var36);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.method_return method() throws RecognitionException {
//        smaliParser.method_return retval = new smaliParser.method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token METHOD_DIRECTIVE28 = null;
//        Token END_METHOD_DIRECTIVE33 = null;
//        ParserRuleReturnScope access_list29 = null;
//        ParserRuleReturnScope member_name30 = null;
//        ParserRuleReturnScope method_prototype31 = null;
//        ParserRuleReturnScope statements_and_directives32 = null;
//        CommonTree METHOD_DIRECTIVE28_tree = null;
//        CommonTree END_METHOD_DIRECTIVE33_tree = null;
//        RewriteRuleTokenStream stream_END_METHOD_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_METHOD_DIRECTIVE");
//        RewriteRuleTokenStream stream_METHOD_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token METHOD_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_method_prototype = new RewriteRuleSubtreeStream(this.adaptor, "rule method_prototype");
//        RewriteRuleSubtreeStream stream_access_list = new RewriteRuleSubtreeStream(this.adaptor, "rule access_list");
//        RewriteRuleSubtreeStream stream_member_name = new RewriteRuleSubtreeStream(this.adaptor, "rule member_name");
//        RewriteRuleSubtreeStream stream_statements_and_directives = new RewriteRuleSubtreeStream(this.adaptor, "rule statements_and_directives");
//
//        try {
//            try {
//                METHOD_DIRECTIVE28 = (Token)this.match(this.input, 180, FOLLOW_METHOD_DIRECTIVE_in_method1508);
//                stream_METHOD_DIRECTIVE.add(METHOD_DIRECTIVE28);
//                this.pushFollow(FOLLOW_access_list_in_method1510);
//                access_list29 = this.access_list();
//                --this.state._fsp;
//                stream_access_list.add(access_list29.getTree());
//                this.pushFollow(FOLLOW_member_name_in_method1512);
//                member_name30 = this.member_name();
//                --this.state._fsp;
//                stream_member_name.add(member_name30.getTree());
//                this.pushFollow(FOLLOW_method_prototype_in_method1514);
//                method_prototype31 = this.method_prototype();
//                --this.state._fsp;
//                stream_method_prototype.add(method_prototype31.getTree());
//                this.pushFollow(FOLLOW_statements_and_directives_in_method1516);
//                statements_and_directives32 = this.statements_and_directives();
//                --this.state._fsp;
//                stream_statements_and_directives.add(statements_and_directives32.getTree());
//                END_METHOD_DIRECTIVE33 = (Token)this.match(this.input, 28, FOLLOW_END_METHOD_DIRECTIVE_in_method1522);
//                stream_END_METHOD_DIRECTIVE.add(END_METHOD_DIRECTIVE33);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(117, retval.start, "I_METHOD"), root_1);
//                this.adaptor.addChild(root_1, stream_member_name.nextTree());
//                this.adaptor.addChild(root_1, stream_method_prototype.nextTree());
//                this.adaptor.addChild(root_1, stream_access_list.nextTree());
//                this.adaptor.addChild(root_1, stream_statements_and_directives.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var22) {
//                this.reportError(var22);
//                this.recover(this.input, var22);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var22);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.statements_and_directives_return statements_and_directives() throws RecognitionException {
//        this.statements_and_directives_stack.push(new smaliParser.statements_and_directives_scope());
//        smaliParser.statements_and_directives_return retval = new smaliParser.statements_and_directives_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        ParserRuleReturnScope ordered_method_item34 = null;
//        ParserRuleReturnScope registers_directive35 = null;
//        ParserRuleReturnScope catch_directive36 = null;
//        ParserRuleReturnScope catchall_directive37 = null;
//        ParserRuleReturnScope parameter_directive38 = null;
//        ParserRuleReturnScope annotation39 = null;
//        RewriteRuleSubtreeStream stream_annotation = new RewriteRuleSubtreeStream(this.adaptor, "rule annotation");
//        RewriteRuleSubtreeStream stream_catchall_directive = new RewriteRuleSubtreeStream(this.adaptor, "rule catchall_directive");
//        RewriteRuleSubtreeStream stream_registers_directive = new RewriteRuleSubtreeStream(this.adaptor, "rule registers_directive");
//        RewriteRuleSubtreeStream stream_catch_directive = new RewriteRuleSubtreeStream(this.adaptor, "rule catch_directive");
//        RewriteRuleSubtreeStream stream_ordered_method_item = new RewriteRuleSubtreeStream(this.adaptor, "rule ordered_method_item");
//        RewriteRuleSubtreeStream stream_parameter_directive = new RewriteRuleSubtreeStream(this.adaptor, "rule parameter_directive");
//
//        try {
//            ((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).hasRegistersDirective = false;
//            ((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).methodAnnotations = new ArrayList();
//
//            while(true) {
//                int alt6 = 7;
//                switch(this.input.LA(1)) {
//                    case 5:
//                        alt6 = 6;
//                    case 6:
//                    case 8:
//                    case 9:
//                    case 10:
//                    case 11:
//                    case 14:
//                    case 15:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 20:
//                    case 21:
//                    case 22:
//                    case 23:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 39:
//                    case 40:
//                    case 41:
//                    case 90:
//                    case 91:
//                    case 92:
//                    case 93:
//                    case 94:
//                    case 95:
//                    case 96:
//                    case 97:
//                    case 98:
//                    case 99:
//                    case 100:
//                    case 101:
//                    case 102:
//                    case 103:
//                    case 104:
//                    case 105:
//                    case 106:
//                    case 107:
//                    case 108:
//                    case 109:
//                    case 110:
//                    case 111:
//                    case 112:
//                    case 113:
//                    case 114:
//                    case 115:
//                    case 116:
//                    case 117:
//                    case 118:
//                    case 119:
//                    case 120:
//                    case 121:
//                    case 122:
//                    case 123:
//                    case 124:
//                    case 125:
//                    case 126:
//                    case 127:
//                    case 128:
//                    case 129:
//                    case 130:
//                    case 131:
//                    case 132:
//                    case 133:
//                    case 134:
//                    case 135:
//                    case 136:
//                    case 137:
//                    case 138:
//                    case 139:
//                    case 140:
//                    case 141:
//                    case 142:
//                    case 143:
//                    case 144:
//                    case 145:
//                    case 146:
//                    case 147:
//                    case 148:
//                    case 149:
//                    case 150:
//                    case 151:
//                    case 152:
//                    case 153:
//                    case 154:
//                    case 155:
//                    case 156:
//                    case 157:
//                    case 158:
//                    case 159:
//                    case 160:
//                    case 161:
//                    case 162:
//                    case 163:
//                    case 164:
//                    case 165:
//                    case 166:
//                    case 167:
//                    case 168:
//                    case 169:
//                    case 170:
//                    case 171:
//                    case 172:
//                    case 173:
//                    case 174:
//                    case 178:
//                    case 179:
//                    case 180:
//                    case 181:
//                    case 182:
//                    case 183:
//                    case 184:
//                    case 187:
//                    case 188:
//                    case 189:
//                    case 191:
//                    case 194:
//                    case 195:
//                    default:
//                        break;
//                    case 7:
//                    case 19:
//                    case 27:
//                    case 34:
//                    case 42:
//                    case 43:
//                    case 44:
//                    case 45:
//                    case 46:
//                    case 47:
//                    case 48:
//                    case 49:
//                    case 50:
//                    case 51:
//                    case 52:
//                    case 53:
//                    case 54:
//                    case 55:
//                    case 56:
//                    case 57:
//                    case 58:
//                    case 59:
//                    case 60:
//                    case 61:
//                    case 62:
//                    case 63:
//                    case 64:
//                    case 65:
//                    case 66:
//                    case 67:
//                    case 68:
//                    case 69:
//                    case 70:
//                    case 71:
//                    case 72:
//                    case 73:
//                    case 74:
//                    case 75:
//                    case 76:
//                    case 77:
//                    case 78:
//                    case 79:
//                    case 80:
//                    case 81:
//                    case 82:
//                    case 83:
//                    case 84:
//                    case 85:
//                    case 86:
//                    case 87:
//                    case 88:
//                    case 89:
//                    case 175:
//                    case 177:
//                    case 185:
//                    case 190:
//                    case 193:
//                    case 196:
//                    case 197:
//                        alt6 = 1;
//                        break;
//                    case 12:
//                        alt6 = 4;
//                        break;
//                    case 13:
//                        alt6 = 3;
//                        break;
//                    case 176:
//                    case 192:
//                        alt6 = 2;
//                        break;
//                    case 186:
//                        alt6 = 5;
//                }
//
//                switch(alt6) {
//                    case 1:
//                        this.pushFollow(FOLLOW_ordered_method_item_in_statements_and_directives1567);
//                        ordered_method_item34 = this.ordered_method_item();
//                        --this.state._fsp;
//                        stream_ordered_method_item.add(ordered_method_item34.getTree());
//                        break;
//                    case 2:
//                        this.pushFollow(FOLLOW_registers_directive_in_statements_and_directives1575);
//                        registers_directive35 = this.registers_directive();
//                        --this.state._fsp;
//                        stream_registers_directive.add(registers_directive35.getTree());
//                        break;
//                    case 3:
//                        this.pushFollow(FOLLOW_catch_directive_in_statements_and_directives1583);
//                        catch_directive36 = this.catch_directive();
//                        --this.state._fsp;
//                        stream_catch_directive.add(catch_directive36.getTree());
//                        break;
//                    case 4:
//                        this.pushFollow(FOLLOW_catchall_directive_in_statements_and_directives1591);
//                        catchall_directive37 = this.catchall_directive();
//                        --this.state._fsp;
//                        stream_catchall_directive.add(catchall_directive37.getTree());
//                        break;
//                    case 5:
//                        this.pushFollow(FOLLOW_parameter_directive_in_statements_and_directives1599);
//                        parameter_directive38 = this.parameter_directive();
//                        --this.state._fsp;
//                        stream_parameter_directive.add(parameter_directive38.getTree());
//                        break;
//                    case 6:
//                        this.pushFollow(FOLLOW_annotation_in_statements_and_directives1607);
//                        annotation39 = this.annotation();
//                        --this.state._fsp;
//                        stream_annotation.add(annotation39.getTree());
//                        ((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).methodAnnotations.add(annotation39 != null ? (CommonTree)annotation39.getTree() : null);
//                        break;
//                    default:
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        if (stream_registers_directive.hasNext()) {
//                            this.adaptor.addChild(root_0, stream_registers_directive.nextTree());
//                        }
//
//                        stream_registers_directive.reset();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(121, "I_ORDERED_METHOD_ITEMS"), root_1);
//
//                        while(stream_ordered_method_item.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_ordered_method_item.nextTree());
//                        }
//
//                        stream_ordered_method_item.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(100, "I_CATCHES"), root_1);
//
//                        while(stream_catch_directive.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_catch_directive.nextTree());
//                        }
//
//                        stream_catch_directive.reset();
//
//                        while(stream_catchall_directive.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_catchall_directive.nextTree());
//                        }
//
//                        stream_catchall_directive.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(125, "I_PARAMETERS"), root_1);
//
//                        while(stream_parameter_directive.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_parameter_directive.nextTree());
//                        }
//
//                        stream_parameter_directive.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        this.adaptor.addChild(root_0, this.buildTree(94, "I_ANNOTATIONS", ((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).methodAnnotations));
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var20) {
//            this.reportError(var20);
//            this.recover(this.input, var20);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//        } finally {
//            this.statements_and_directives_stack.pop();
//        }
//
//        return retval;
//    }
//
//    public final smaliParser.ordered_method_item_return ordered_method_item() throws RecognitionException {
//        smaliParser.ordered_method_item_return retval = new smaliParser.ordered_method_item_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        ParserRuleReturnScope label40 = null;
//        ParserRuleReturnScope instruction41 = null;
//        smaliParser.debug_directive_return debug_directive42 = null;
//
//        try {
//            try {
//                byte alt7;
//                switch(this.input.LA(1)) {
//                    case 7:
//                    case 42:
//                    case 43:
//                    case 44:
//                    case 45:
//                    case 46:
//                    case 47:
//                    case 48:
//                    case 49:
//                    case 50:
//                    case 51:
//                    case 52:
//                    case 53:
//                    case 54:
//                    case 55:
//                    case 56:
//                    case 57:
//                    case 58:
//                    case 59:
//                    case 60:
//                    case 61:
//                    case 62:
//                    case 63:
//                    case 64:
//                    case 65:
//                    case 66:
//                    case 67:
//                    case 68:
//                    case 69:
//                    case 70:
//                    case 71:
//                    case 72:
//                    case 73:
//                    case 74:
//                    case 75:
//                    case 76:
//                    case 77:
//                    case 78:
//                    case 79:
//                    case 80:
//                    case 81:
//                    case 82:
//                    case 83:
//                    case 84:
//                    case 85:
//                    case 86:
//                    case 87:
//                    case 88:
//                    case 89:
//                    case 185:
//                    case 197:
//                        alt7 = 2;
//                        break;
//                    case 8:
//                    case 9:
//                    case 10:
//                    case 11:
//                    case 12:
//                    case 13:
//                    case 14:
//                    case 15:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 20:
//                    case 21:
//                    case 22:
//                    case 23:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 39:
//                    case 40:
//                    case 41:
//                    case 90:
//                    case 91:
//                    case 92:
//                    case 93:
//                    case 94:
//                    case 95:
//                    case 96:
//                    case 97:
//                    case 98:
//                    case 99:
//                    case 100:
//                    case 101:
//                    case 102:
//                    case 103:
//                    case 104:
//                    case 105:
//                    case 106:
//                    case 107:
//                    case 108:
//                    case 109:
//                    case 110:
//                    case 111:
//                    case 112:
//                    case 113:
//                    case 114:
//                    case 115:
//                    case 116:
//                    case 117:
//                    case 118:
//                    case 119:
//                    case 120:
//                    case 121:
//                    case 122:
//                    case 123:
//                    case 124:
//                    case 125:
//                    case 126:
//                    case 127:
//                    case 128:
//                    case 129:
//                    case 130:
//                    case 131:
//                    case 132:
//                    case 133:
//                    case 134:
//                    case 135:
//                    case 136:
//                    case 137:
//                    case 138:
//                    case 139:
//                    case 140:
//                    case 141:
//                    case 142:
//                    case 143:
//                    case 144:
//                    case 145:
//                    case 146:
//                    case 147:
//                    case 148:
//                    case 149:
//                    case 150:
//                    case 151:
//                    case 152:
//                    case 153:
//                    case 154:
//                    case 155:
//                    case 156:
//                    case 157:
//                    case 158:
//                    case 159:
//                    case 160:
//                    case 161:
//                    case 162:
//                    case 163:
//                    case 164:
//                    case 165:
//                    case 166:
//                    case 167:
//                    case 168:
//                    case 169:
//                    case 170:
//                    case 171:
//                    case 172:
//                    case 173:
//                    case 174:
//                    case 176:
//                    case 178:
//                    case 179:
//                    case 180:
//                    case 181:
//                    case 182:
//                    case 183:
//                    case 184:
//                    case 186:
//                    case 187:
//                    case 188:
//                    case 189:
//                    case 191:
//                    case 192:
//                    case 194:
//                    case 195:
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 7, 0, this.input);
//                        throw nvae;
//                    case 19:
//                        alt7 = 1;
//                        break;
//                    case 27:
//                    case 34:
//                    case 175:
//                    case 177:
//                    case 190:
//                    case 193:
//                    case 196:
//                        alt7 = 3;
//                }
//
//                switch(alt7) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_label_in_ordered_method_item1692);
//                        label40 = this.label();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, label40.getTree());
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_instruction_in_ordered_method_item1698);
//                        instruction41 = this.instruction();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, instruction41.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_debug_directive_in_ordered_method_item1704);
//                        debug_directive42 = this.debug_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, debug_directive42.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var11) {
//                this.reportError(var11);
//                this.recover(this.input, var11);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.registers_directive_return registers_directive() throws RecognitionException {
//        smaliParser.registers_directive_return retval = new smaliParser.registers_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token directive = null;
//        ParserRuleReturnScope regCount = null;
//        ParserRuleReturnScope regCount2 = null;
//        CommonTree directive_tree = null;
//        RewriteRuleTokenStream stream_LOCALS_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token LOCALS_DIRECTIVE");
//        RewriteRuleTokenStream stream_REGISTERS_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token REGISTERS_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_integral_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule integral_literal");
//
//        try {
//            try {
//                int LA8_0 = this.input.LA(1);
//                byte alt8;
//                if (LA8_0 == 192) {
//                    alt8 = 1;
//                } else {
//                    if (LA8_0 != 176) {
//                        NoViableAltException nvae = new NoViableAltException("", 8, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt8 = 2;
//                }
//
//                RewriteRuleSubtreeStream stream_regCount2;
//                CommonTree root_1;
//                switch(alt8) {
//                    case 1:
//                        directive = (Token)this.match(this.input, 192, FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1724);
//                        stream_REGISTERS_DIRECTIVE.add(directive);
//                        this.pushFollow(FOLLOW_integral_literal_in_registers_directive1728);
//                        regCount = this.integral_literal();
//                        --this.state._fsp;
//                        stream_integral_literal.add(regCount.getTree());
//                        retval.tree = root_0;
//                        stream_regCount2 = new RewriteRuleSubtreeStream(this.adaptor, "rule regCount", regCount != null ? regCount.getTree() : null);
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(128, directive, "I_REGISTERS"), root_1);
//                        this.adaptor.addChild(root_1, stream_regCount2.nextTree());
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        break;
//                    case 2:
//                        directive = (Token)this.match(this.input, 176, FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1748);
//                        stream_LOCALS_DIRECTIVE.add(directive);
//                        this.pushFollow(FOLLOW_integral_literal_in_registers_directive1752);
//                        regCount2 = this.integral_literal();
//                        --this.state._fsp;
//                        stream_integral_literal.add(regCount2.getTree());
//                        retval.tree = root_0;
//                        stream_regCount2 = new RewriteRuleSubtreeStream(this.adaptor, "rule regCount2", regCount2 != null ? regCount2.getTree() : null);
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(116, directive, "I_LOCALS"), root_1);
//                        this.adaptor.addChild(root_1, stream_regCount2.nextTree());
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                }
//
//                if (((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).hasRegistersDirective) {
//                    throw new SemanticException(this.input, directive, "There can only be a single .registers or .locals directive in a method", new Object[0]);
//                }
//
//                ((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).hasRegistersDirective = true;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.param_list_or_id_return param_list_or_id() throws RecognitionException {
//        smaliParser.param_list_or_id_return retval = new smaliParser.param_list_or_id_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token PARAM_LIST_OR_ID_PRIMITIVE_TYPE43 = null;
//        CommonTree PARAM_LIST_OR_ID_PRIMITIVE_TYPE43_tree = null;
//
//        try {
//            root_0 = (CommonTree)this.adaptor.nil();
//            int cnt9 = 0;
//
//            while(true) {
//                int alt9 = 2;
//                int LA9_0 = this.input.LA(1);
//                if (LA9_0 == 187) {
//                    alt9 = 1;
//                }
//
//                switch(alt9) {
//                    case 1:
//                        PARAM_LIST_OR_ID_PRIMITIVE_TYPE43 = (Token)this.match(this.input, 187, FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id1784);
//                        PARAM_LIST_OR_ID_PRIMITIVE_TYPE43_tree = (CommonTree)this.adaptor.create(PARAM_LIST_OR_ID_PRIMITIVE_TYPE43);
//                        this.adaptor.addChild(root_0, PARAM_LIST_OR_ID_PRIMITIVE_TYPE43_tree);
//                        ++cnt9;
//                        break;
//                    default:
//                        if (cnt9 < 1) {
//                            EarlyExitException eee = new EarlyExitException(9, this.input);
//                            throw eee;
//                        }
//
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var12) {
//            this.reportError(var12);
//            this.recover(this.input, var12);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var12);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.simple_name_return simple_name() throws RecognitionException {
//        smaliParser.simple_name_return retval = new smaliParser.simple_name_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token SIMPLE_NAME44 = null;
//        Token ACCESS_SPEC45 = null;
//        Token VERIFICATION_ERROR_TYPE46 = null;
//        Token POSITIVE_INTEGER_LITERAL47 = null;
//        Token NEGATIVE_INTEGER_LITERAL48 = null;
//        Token FLOAT_LITERAL_OR_ID49 = null;
//        Token DOUBLE_LITERAL_OR_ID50 = null;
//        Token BOOL_LITERAL51 = null;
//        Token NULL_LITERAL52 = null;
//        Token REGISTER53 = null;
//        Token PRIMITIVE_TYPE55 = null;
//        Token VOID_TYPE56 = null;
//        Token ANNOTATION_VISIBILITY57 = null;
//        Token INSTRUCTION_FORMAT10t58 = null;
//        Token INSTRUCTION_FORMAT10x59 = null;
//        Token INSTRUCTION_FORMAT10x_ODEX60 = null;
//        Token INSTRUCTION_FORMAT11x61 = null;
//        Token INSTRUCTION_FORMAT12x_OR_ID62 = null;
//        Token INSTRUCTION_FORMAT21c_FIELD63 = null;
//        Token INSTRUCTION_FORMAT21c_FIELD_ODEX64 = null;
//        Token INSTRUCTION_FORMAT21c_STRING65 = null;
//        Token INSTRUCTION_FORMAT21c_TYPE66 = null;
//        Token INSTRUCTION_FORMAT21c_LAMBDA67 = null;
//        Token INSTRUCTION_FORMAT21c_METHOD68 = null;
//        Token INSTRUCTION_FORMAT21t69 = null;
//        Token INSTRUCTION_FORMAT22c_FIELD70 = null;
//        Token INSTRUCTION_FORMAT22c_FIELD_ODEX71 = null;
//        Token INSTRUCTION_FORMAT22c_TYPE72 = null;
//        Token INSTRUCTION_FORMAT22c_STRING73 = null;
//        Token INSTRUCTION_FORMAT22cs_FIELD74 = null;
//        Token INSTRUCTION_FORMAT22s_OR_ID75 = null;
//        Token INSTRUCTION_FORMAT22t76 = null;
//        Token INSTRUCTION_FORMAT23x77 = null;
//        Token INSTRUCTION_FORMAT25x78 = null;
//        Token INSTRUCTION_FORMAT31i_OR_ID79 = null;
//        Token INSTRUCTION_FORMAT31t80 = null;
//        Token INSTRUCTION_FORMAT35c_METHOD81 = null;
//        Token INSTRUCTION_FORMAT35c_METHOD_ODEX82 = null;
//        Token INSTRUCTION_FORMAT35c_TYPE83 = null;
//        Token INSTRUCTION_FORMAT35mi_METHOD84 = null;
//        Token INSTRUCTION_FORMAT35ms_METHOD85 = null;
//        Token INSTRUCTION_FORMAT51l86 = null;
//        ParserRuleReturnScope param_list_or_id54 = null;
//        CommonTree SIMPLE_NAME44_tree = null;
//        CommonTree ACCESS_SPEC45_tree = null;
//        CommonTree VERIFICATION_ERROR_TYPE46_tree = null;
//        CommonTree POSITIVE_INTEGER_LITERAL47_tree = null;
//        CommonTree NEGATIVE_INTEGER_LITERAL48_tree = null;
//        CommonTree FLOAT_LITERAL_OR_ID49_tree = null;
//        CommonTree DOUBLE_LITERAL_OR_ID50_tree = null;
//        CommonTree BOOL_LITERAL51_tree = null;
//        CommonTree NULL_LITERAL52_tree = null;
//        CommonTree REGISTER53_tree = null;
//        CommonTree PRIMITIVE_TYPE55_tree = null;
//        CommonTree VOID_TYPE56_tree = null;
//        CommonTree ANNOTATION_VISIBILITY57_tree = null;
//        CommonTree INSTRUCTION_FORMAT10t58_tree = null;
//        CommonTree INSTRUCTION_FORMAT10x59_tree = null;
//        CommonTree INSTRUCTION_FORMAT10x_ODEX60_tree = null;
//        CommonTree INSTRUCTION_FORMAT11x61_tree = null;
//        CommonTree INSTRUCTION_FORMAT12x_OR_ID62_tree = null;
//        CommonTree INSTRUCTION_FORMAT21c_FIELD63_tree = null;
//        CommonTree INSTRUCTION_FORMAT21c_FIELD_ODEX64_tree = null;
//        CommonTree INSTRUCTION_FORMAT21c_STRING65_tree = null;
//        CommonTree INSTRUCTION_FORMAT21c_TYPE66_tree = null;
//        CommonTree INSTRUCTION_FORMAT21c_LAMBDA67_tree = null;
//        CommonTree INSTRUCTION_FORMAT21c_METHOD68_tree = null;
//        CommonTree INSTRUCTION_FORMAT21t69_tree = null;
//        CommonTree INSTRUCTION_FORMAT22c_FIELD70_tree = null;
//        CommonTree INSTRUCTION_FORMAT22c_FIELD_ODEX71_tree = null;
//        CommonTree INSTRUCTION_FORMAT22c_TYPE72_tree = null;
//        CommonTree INSTRUCTION_FORMAT22c_STRING73_tree = null;
//        CommonTree INSTRUCTION_FORMAT22cs_FIELD74_tree = null;
//        CommonTree INSTRUCTION_FORMAT22s_OR_ID75_tree = null;
//        CommonTree INSTRUCTION_FORMAT22t76_tree = null;
//        CommonTree INSTRUCTION_FORMAT23x77_tree = null;
//        CommonTree INSTRUCTION_FORMAT25x78_tree = null;
//        CommonTree INSTRUCTION_FORMAT31i_OR_ID79_tree = null;
//        CommonTree INSTRUCTION_FORMAT31t80_tree = null;
//        CommonTree INSTRUCTION_FORMAT35c_METHOD81_tree = null;
//        CommonTree INSTRUCTION_FORMAT35c_METHOD_ODEX82_tree = null;
//        CommonTree INSTRUCTION_FORMAT35c_TYPE83_tree = null;
//        CommonTree INSTRUCTION_FORMAT35mi_METHOD84_tree = null;
//        CommonTree INSTRUCTION_FORMAT35ms_METHOD85_tree = null;
//        CommonTree INSTRUCTION_FORMAT51l86_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_LAMBDA = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_LAMBDA");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_STRING = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_STRING");
//        RewriteRuleTokenStream stream_ANNOTATION_VISIBILITY = new RewriteRuleTokenStream(this.adaptor, "token ANNOTATION_VISIBILITY");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22t");
//        RewriteRuleTokenStream stream_VOID_TYPE = new RewriteRuleTokenStream(this.adaptor, "token VOID_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT10t");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35mi_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35mi_METHOD");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22s_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22s_OR_ID");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22cs_FIELD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22cs_FIELD");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT12x_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT12x_OR_ID");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35ms_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35ms_METHOD");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35c_METHOD");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35c_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT10x");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_METHOD");
//        RewriteRuleTokenStream stream_FLOAT_LITERAL_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token FLOAT_LITERAL_OR_ID");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_STRING = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_STRING");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD_ODEX = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35c_METHOD_ODEX");
//        RewriteRuleTokenStream stream_NEGATIVE_INTEGER_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token NEGATIVE_INTEGER_LITERAL");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD_ODEX = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_FIELD_ODEX");
//        RewriteRuleTokenStream stream_DOUBLE_LITERAL_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token DOUBLE_LITERAL_OR_ID");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31i_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT31i_OR_ID");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21t");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT25x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT25x");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT31t");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT23x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT23x");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT51l = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT51l");
//        RewriteRuleTokenStream stream_POSITIVE_INTEGER_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token POSITIVE_INTEGER_LITERAL");
//        RewriteRuleTokenStream stream_BOOL_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token BOOL_LITERAL");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x_ODEX = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT10x_ODEX");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_FIELD");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_FIELD");
//        RewriteRuleTokenStream stream_VERIFICATION_ERROR_TYPE = new RewriteRuleTokenStream(this.adaptor, "token VERIFICATION_ERROR_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT11x");
//        RewriteRuleTokenStream stream_ACCESS_SPEC = new RewriteRuleTokenStream(this.adaptor, "token ACCESS_SPEC");
//        RewriteRuleTokenStream stream_NULL_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token NULL_LITERAL");
//        RewriteRuleTokenStream stream_PRIMITIVE_TYPE = new RewriteRuleTokenStream(this.adaptor, "token PRIMITIVE_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD_ODEX = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_FIELD_ODEX");
//        RewriteRuleSubtreeStream stream_param_list_or_id = new RewriteRuleSubtreeStream(this.adaptor, "rule param_list_or_id");
//
//        try {
//            try {
//                byte alt10;
//                switch(this.input.LA(1)) {
//                    case 4:
//                        alt10 = 2;
//                        break;
//                    case 5:
//                    case 7:
//                    case 8:
//                    case 9:
//                    case 11:
//                    case 12:
//                    case 13:
//                    case 14:
//                    case 15:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 19:
//                    case 20:
//                    case 21:
//                    case 22:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 27:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 34:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 40:
//                    case 41:
//                    case 45:
//                    case 47:
//                    case 49:
//                    case 50:
//                    case 57:
//                    case 58:
//                    case 59:
//                    case 61:
//                    case 67:
//                    case 70:
//                    case 73:
//                    case 74:
//                    case 75:
//                    case 78:
//                    case 84:
//                    case 85:
//                    case 86:
//                    case 87:
//                    case 88:
//                    case 90:
//                    case 91:
//                    case 92:
//                    case 93:
//                    case 94:
//                    case 95:
//                    case 96:
//                    case 97:
//                    case 98:
//                    case 99:
//                    case 100:
//                    case 101:
//                    case 102:
//                    case 103:
//                    case 104:
//                    case 105:
//                    case 106:
//                    case 107:
//                    case 108:
//                    case 109:
//                    case 110:
//                    case 111:
//                    case 112:
//                    case 113:
//                    case 114:
//                    case 115:
//                    case 116:
//                    case 117:
//                    case 118:
//                    case 119:
//                    case 120:
//                    case 121:
//                    case 122:
//                    case 123:
//                    case 124:
//                    case 125:
//                    case 126:
//                    case 127:
//                    case 128:
//                    case 129:
//                    case 130:
//                    case 131:
//                    case 132:
//                    case 133:
//                    case 134:
//                    case 135:
//                    case 136:
//                    case 137:
//                    case 138:
//                    case 139:
//                    case 140:
//                    case 141:
//                    case 142:
//                    case 143:
//                    case 144:
//                    case 145:
//                    case 146:
//                    case 147:
//                    case 148:
//                    case 149:
//                    case 150:
//                    case 151:
//                    case 152:
//                    case 153:
//                    case 154:
//                    case 155:
//                    case 156:
//                    case 157:
//                    case 158:
//                    case 159:
//                    case 160:
//                    case 161:
//                    case 162:
//                    case 163:
//                    case 164:
//                    case 165:
//                    case 166:
//                    case 167:
//                    case 168:
//                    case 169:
//                    case 170:
//                    case 171:
//                    case 172:
//                    case 173:
//                    case 174:
//                    case 175:
//                    case 176:
//                    case 177:
//                    case 178:
//                    case 179:
//                    case 180:
//                    case 183:
//                    case 184:
//                    case 185:
//                    case 186:
//                    case 190:
//                    case 192:
//                    case 193:
//                    case 194:
//                    case 196:
//                    case 197:
//                    case 198:
//                    case 199:
//                    case 200:
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 10, 0, this.input);
//                        throw nvae;
//                    case 6:
//                        alt10 = 14;
//                        break;
//                    case 10:
//                        alt10 = 8;
//                        break;
//                    case 23:
//                        alt10 = 7;
//                        break;
//                    case 39:
//                        alt10 = 6;
//                        break;
//                    case 42:
//                        alt10 = 15;
//                        break;
//                    case 43:
//                        alt10 = 16;
//                        break;
//                    case 44:
//                        alt10 = 17;
//                        break;
//                    case 46:
//                        alt10 = 18;
//                        break;
//                    case 48:
//                        alt10 = 19;
//                        break;
//                    case 51:
//                        alt10 = 20;
//                        break;
//                    case 52:
//                        alt10 = 21;
//                        break;
//                    case 53:
//                        alt10 = 24;
//                        break;
//                    case 54:
//                        alt10 = 25;
//                        break;
//                    case 55:
//                        alt10 = 22;
//                        break;
//                    case 56:
//                        alt10 = 23;
//                        break;
//                    case 60:
//                        alt10 = 26;
//                        break;
//                    case 62:
//                        alt10 = 27;
//                        break;
//                    case 63:
//                        alt10 = 28;
//                        break;
//                    case 64:
//                        alt10 = 30;
//                        break;
//                    case 65:
//                        alt10 = 29;
//                        break;
//                    case 66:
//                        alt10 = 31;
//                        break;
//                    case 68:
//                        alt10 = 32;
//                        break;
//                    case 69:
//                        alt10 = 33;
//                        break;
//                    case 71:
//                        alt10 = 34;
//                        break;
//                    case 72:
//                        alt10 = 35;
//                        break;
//                    case 76:
//                        alt10 = 36;
//                        break;
//                    case 77:
//                        alt10 = 37;
//                        break;
//                    case 79:
//                        alt10 = 38;
//                        break;
//                    case 80:
//                        alt10 = 39;
//                        break;
//                    case 81:
//                        alt10 = 40;
//                        break;
//                    case 82:
//                        alt10 = 41;
//                        break;
//                    case 83:
//                        alt10 = 42;
//                        break;
//                    case 89:
//                        alt10 = 43;
//                        break;
//                    case 181:
//                        alt10 = 5;
//                        break;
//                    case 182:
//                        alt10 = 9;
//                        break;
//                    case 187:
//                        alt10 = 11;
//                        break;
//                    case 188:
//                        alt10 = 4;
//                        break;
//                    case 189:
//                        alt10 = 12;
//                        break;
//                    case 191:
//                        alt10 = 10;
//                        break;
//                    case 195:
//                        alt10 = 1;
//                        break;
//                    case 201:
//                        alt10 = 3;
//                        break;
//                    case 202:
//                        alt10 = 13;
//                }
//
//                switch(alt10) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        SIMPLE_NAME44 = (Token)this.match(this.input, 195, FOLLOW_SIMPLE_NAME_in_simple_name1797);
//                        SIMPLE_NAME44_tree = (CommonTree)this.adaptor.create(SIMPLE_NAME44);
//                        this.adaptor.addChild(root_0, SIMPLE_NAME44_tree);
//                        break;
//                    case 2:
//                        ACCESS_SPEC45 = (Token)this.match(this.input, 4, FOLLOW_ACCESS_SPEC_in_simple_name1803);
//                        stream_ACCESS_SPEC.add(ACCESS_SPEC45);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, ACCESS_SPEC45));
//                        retval.tree = root_0;
//                        break;
//                    case 3:
//                        VERIFICATION_ERROR_TYPE46 = (Token)this.match(this.input, 201, FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1814);
//                        stream_VERIFICATION_ERROR_TYPE.add(VERIFICATION_ERROR_TYPE46);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, VERIFICATION_ERROR_TYPE46));
//                        retval.tree = root_0;
//                        break;
//                    case 4:
//                        POSITIVE_INTEGER_LITERAL47 = (Token)this.match(this.input, 188, FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1825);
//                        stream_POSITIVE_INTEGER_LITERAL.add(POSITIVE_INTEGER_LITERAL47);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, POSITIVE_INTEGER_LITERAL47));
//                        retval.tree = root_0;
//                        break;
//                    case 5:
//                        NEGATIVE_INTEGER_LITERAL48 = (Token)this.match(this.input, 181, FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1836);
//                        stream_NEGATIVE_INTEGER_LITERAL.add(NEGATIVE_INTEGER_LITERAL48);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, NEGATIVE_INTEGER_LITERAL48));
//                        retval.tree = root_0;
//                        break;
//                    case 6:
//                        FLOAT_LITERAL_OR_ID49 = (Token)this.match(this.input, 39, FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1847);
//                        stream_FLOAT_LITERAL_OR_ID.add(FLOAT_LITERAL_OR_ID49);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, FLOAT_LITERAL_OR_ID49));
//                        retval.tree = root_0;
//                        break;
//                    case 7:
//                        DOUBLE_LITERAL_OR_ID50 = (Token)this.match(this.input, 23, FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1858);
//                        stream_DOUBLE_LITERAL_OR_ID.add(DOUBLE_LITERAL_OR_ID50);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, DOUBLE_LITERAL_OR_ID50));
//                        retval.tree = root_0;
//                        break;
//                    case 8:
//                        BOOL_LITERAL51 = (Token)this.match(this.input, 10, FOLLOW_BOOL_LITERAL_in_simple_name1869);
//                        stream_BOOL_LITERAL.add(BOOL_LITERAL51);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, BOOL_LITERAL51));
//                        retval.tree = root_0;
//                        break;
//                    case 9:
//                        NULL_LITERAL52 = (Token)this.match(this.input, 182, FOLLOW_NULL_LITERAL_in_simple_name1880);
//                        stream_NULL_LITERAL.add(NULL_LITERAL52);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, NULL_LITERAL52));
//                        retval.tree = root_0;
//                        break;
//                    case 10:
//                        REGISTER53 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_simple_name1891);
//                        stream_REGISTER.add(REGISTER53);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, REGISTER53));
//                        retval.tree = root_0;
//                        break;
//                    case 11:
//                        this.pushFollow(FOLLOW_param_list_or_id_in_simple_name1902);
//                        param_list_or_id54 = this.param_list_or_id();
//                        --this.state._fsp;
//                        stream_param_list_or_id.add(param_list_or_id54.getTree());
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, this.adaptor.create(195, param_list_or_id54 != null ? this.input.toString(param_list_or_id54.start, param_list_or_id54.stop) : null));
//                        retval.tree = root_0;
//                        break;
//                    case 12:
//                        PRIMITIVE_TYPE55 = (Token)this.match(this.input, 189, FOLLOW_PRIMITIVE_TYPE_in_simple_name1912);
//                        stream_PRIMITIVE_TYPE.add(PRIMITIVE_TYPE55);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, PRIMITIVE_TYPE55));
//                        retval.tree = root_0;
//                        break;
//                    case 13:
//                        VOID_TYPE56 = (Token)this.match(this.input, 202, FOLLOW_VOID_TYPE_in_simple_name1923);
//                        stream_VOID_TYPE.add(VOID_TYPE56);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, VOID_TYPE56));
//                        retval.tree = root_0;
//                        break;
//                    case 14:
//                        ANNOTATION_VISIBILITY57 = (Token)this.match(this.input, 6, FOLLOW_ANNOTATION_VISIBILITY_in_simple_name1934);
//                        stream_ANNOTATION_VISIBILITY.add(ANNOTATION_VISIBILITY57);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, ANNOTATION_VISIBILITY57));
//                        retval.tree = root_0;
//                        break;
//                    case 15:
//                        INSTRUCTION_FORMAT10t58 = (Token)this.match(this.input, 42, FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name1945);
//                        stream_INSTRUCTION_FORMAT10t.add(INSTRUCTION_FORMAT10t58);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT10t58));
//                        retval.tree = root_0;
//                        break;
//                    case 16:
//                        INSTRUCTION_FORMAT10x59 = (Token)this.match(this.input, 43, FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name1956);
//                        stream_INSTRUCTION_FORMAT10x.add(INSTRUCTION_FORMAT10x59);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT10x59));
//                        retval.tree = root_0;
//                        break;
//                    case 17:
//                        INSTRUCTION_FORMAT10x_ODEX60 = (Token)this.match(this.input, 44, FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name1967);
//                        stream_INSTRUCTION_FORMAT10x_ODEX.add(INSTRUCTION_FORMAT10x_ODEX60);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT10x_ODEX60));
//                        retval.tree = root_0;
//                        break;
//                    case 18:
//                        INSTRUCTION_FORMAT11x61 = (Token)this.match(this.input, 46, FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name1978);
//                        stream_INSTRUCTION_FORMAT11x.add(INSTRUCTION_FORMAT11x61);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT11x61));
//                        retval.tree = root_0;
//                        break;
//                    case 19:
//                        INSTRUCTION_FORMAT12x_OR_ID62 = (Token)this.match(this.input, 48, FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name1989);
//                        stream_INSTRUCTION_FORMAT12x_OR_ID.add(INSTRUCTION_FORMAT12x_OR_ID62);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT12x_OR_ID62));
//                        retval.tree = root_0;
//                        break;
//                    case 20:
//                        INSTRUCTION_FORMAT21c_FIELD63 = (Token)this.match(this.input, 51, FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2000);
//                        stream_INSTRUCTION_FORMAT21c_FIELD.add(INSTRUCTION_FORMAT21c_FIELD63);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21c_FIELD63));
//                        retval.tree = root_0;
//                        break;
//                    case 21:
//                        INSTRUCTION_FORMAT21c_FIELD_ODEX64 = (Token)this.match(this.input, 52, FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2011);
//                        stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.add(INSTRUCTION_FORMAT21c_FIELD_ODEX64);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21c_FIELD_ODEX64));
//                        retval.tree = root_0;
//                        break;
//                    case 22:
//                        INSTRUCTION_FORMAT21c_STRING65 = (Token)this.match(this.input, 55, FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2022);
//                        stream_INSTRUCTION_FORMAT21c_STRING.add(INSTRUCTION_FORMAT21c_STRING65);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21c_STRING65));
//                        retval.tree = root_0;
//                        break;
//                    case 23:
//                        INSTRUCTION_FORMAT21c_TYPE66 = (Token)this.match(this.input, 56, FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2033);
//                        stream_INSTRUCTION_FORMAT21c_TYPE.add(INSTRUCTION_FORMAT21c_TYPE66);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21c_TYPE66));
//                        retval.tree = root_0;
//                        break;
//                    case 24:
//                        INSTRUCTION_FORMAT21c_LAMBDA67 = (Token)this.match(this.input, 53, FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_simple_name2044);
//                        stream_INSTRUCTION_FORMAT21c_LAMBDA.add(INSTRUCTION_FORMAT21c_LAMBDA67);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21c_LAMBDA67));
//                        retval.tree = root_0;
//                        break;
//                    case 25:
//                        INSTRUCTION_FORMAT21c_METHOD68 = (Token)this.match(this.input, 54, FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_simple_name2055);
//                        stream_INSTRUCTION_FORMAT21c_METHOD.add(INSTRUCTION_FORMAT21c_METHOD68);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21c_METHOD68));
//                        retval.tree = root_0;
//                        break;
//                    case 26:
//                        INSTRUCTION_FORMAT21t69 = (Token)this.match(this.input, 60, FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2066);
//                        stream_INSTRUCTION_FORMAT21t.add(INSTRUCTION_FORMAT21t69);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT21t69));
//                        retval.tree = root_0;
//                        break;
//                    case 27:
//                        INSTRUCTION_FORMAT22c_FIELD70 = (Token)this.match(this.input, 62, FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2077);
//                        stream_INSTRUCTION_FORMAT22c_FIELD.add(INSTRUCTION_FORMAT22c_FIELD70);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22c_FIELD70));
//                        retval.tree = root_0;
//                        break;
//                    case 28:
//                        INSTRUCTION_FORMAT22c_FIELD_ODEX71 = (Token)this.match(this.input, 63, FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2088);
//                        stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.add(INSTRUCTION_FORMAT22c_FIELD_ODEX71);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22c_FIELD_ODEX71));
//                        retval.tree = root_0;
//                        break;
//                    case 29:
//                        INSTRUCTION_FORMAT22c_TYPE72 = (Token)this.match(this.input, 65, FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2099);
//                        stream_INSTRUCTION_FORMAT22c_TYPE.add(INSTRUCTION_FORMAT22c_TYPE72);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22c_TYPE72));
//                        retval.tree = root_0;
//                        break;
//                    case 30:
//                        INSTRUCTION_FORMAT22c_STRING73 = (Token)this.match(this.input, 64, FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_simple_name2110);
//                        stream_INSTRUCTION_FORMAT22c_STRING.add(INSTRUCTION_FORMAT22c_STRING73);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22c_STRING73));
//                        retval.tree = root_0;
//                        break;
//                    case 31:
//                        INSTRUCTION_FORMAT22cs_FIELD74 = (Token)this.match(this.input, 66, FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2121);
//                        stream_INSTRUCTION_FORMAT22cs_FIELD.add(INSTRUCTION_FORMAT22cs_FIELD74);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22cs_FIELD74));
//                        retval.tree = root_0;
//                        break;
//                    case 32:
//                        INSTRUCTION_FORMAT22s_OR_ID75 = (Token)this.match(this.input, 68, FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2132);
//                        stream_INSTRUCTION_FORMAT22s_OR_ID.add(INSTRUCTION_FORMAT22s_OR_ID75);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22s_OR_ID75));
//                        retval.tree = root_0;
//                        break;
//                    case 33:
//                        INSTRUCTION_FORMAT22t76 = (Token)this.match(this.input, 69, FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2143);
//                        stream_INSTRUCTION_FORMAT22t.add(INSTRUCTION_FORMAT22t76);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT22t76));
//                        retval.tree = root_0;
//                        break;
//                    case 34:
//                        INSTRUCTION_FORMAT23x77 = (Token)this.match(this.input, 71, FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2154);
//                        stream_INSTRUCTION_FORMAT23x.add(INSTRUCTION_FORMAT23x77);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT23x77));
//                        retval.tree = root_0;
//                        break;
//                    case 35:
//                        INSTRUCTION_FORMAT25x78 = (Token)this.match(this.input, 72, FOLLOW_INSTRUCTION_FORMAT25x_in_simple_name2165);
//                        stream_INSTRUCTION_FORMAT25x.add(INSTRUCTION_FORMAT25x78);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT25x78));
//                        retval.tree = root_0;
//                        break;
//                    case 36:
//                        INSTRUCTION_FORMAT31i_OR_ID79 = (Token)this.match(this.input, 76, FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2176);
//                        stream_INSTRUCTION_FORMAT31i_OR_ID.add(INSTRUCTION_FORMAT31i_OR_ID79);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT31i_OR_ID79));
//                        retval.tree = root_0;
//                        break;
//                    case 37:
//                        INSTRUCTION_FORMAT31t80 = (Token)this.match(this.input, 77, FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2187);
//                        stream_INSTRUCTION_FORMAT31t.add(INSTRUCTION_FORMAT31t80);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT31t80));
//                        retval.tree = root_0;
//                        break;
//                    case 38:
//                        INSTRUCTION_FORMAT35c_METHOD81 = (Token)this.match(this.input, 79, FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2198);
//                        stream_INSTRUCTION_FORMAT35c_METHOD.add(INSTRUCTION_FORMAT35c_METHOD81);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT35c_METHOD81));
//                        retval.tree = root_0;
//                        break;
//                    case 39:
//                        INSTRUCTION_FORMAT35c_METHOD_ODEX82 = (Token)this.match(this.input, 80, FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2209);
//                        stream_INSTRUCTION_FORMAT35c_METHOD_ODEX.add(INSTRUCTION_FORMAT35c_METHOD_ODEX82);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT35c_METHOD_ODEX82));
//                        retval.tree = root_0;
//                        break;
//                    case 40:
//                        INSTRUCTION_FORMAT35c_TYPE83 = (Token)this.match(this.input, 81, FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2220);
//                        stream_INSTRUCTION_FORMAT35c_TYPE.add(INSTRUCTION_FORMAT35c_TYPE83);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT35c_TYPE83));
//                        retval.tree = root_0;
//                        break;
//                    case 41:
//                        INSTRUCTION_FORMAT35mi_METHOD84 = (Token)this.match(this.input, 82, FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2231);
//                        stream_INSTRUCTION_FORMAT35mi_METHOD.add(INSTRUCTION_FORMAT35mi_METHOD84);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT35mi_METHOD84));
//                        retval.tree = root_0;
//                        break;
//                    case 42:
//                        INSTRUCTION_FORMAT35ms_METHOD85 = (Token)this.match(this.input, 83, FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2242);
//                        stream_INSTRUCTION_FORMAT35ms_METHOD.add(INSTRUCTION_FORMAT35ms_METHOD85);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT35ms_METHOD85));
//                        retval.tree = root_0;
//                        break;
//                    case 43:
//                        INSTRUCTION_FORMAT51l86 = (Token)this.match(this.input, 89, FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2253);
//                        stream_INSTRUCTION_FORMAT51l.add(INSTRUCTION_FORMAT51l86);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, INSTRUCTION_FORMAT51l86));
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var135) {
//                this.reportError(var135);
//                this.recover(this.input, var135);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var135);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.member_name_return member_name() throws RecognitionException {
//        smaliParser.member_name_return retval = new smaliParser.member_name_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token MEMBER_NAME88 = null;
//        ParserRuleReturnScope simple_name87 = null;
//        CommonTree MEMBER_NAME88_tree = null;
//        RewriteRuleTokenStream stream_MEMBER_NAME = new RewriteRuleTokenStream(this.adaptor, "token MEMBER_NAME");
//
//        try {
//            try {
//                int LA11_0 = this.input.LA(1);
//                byte alt11;
//                if (LA11_0 != 4 && LA11_0 != 6 && LA11_0 != 10 && LA11_0 != 23 && LA11_0 != 39 && (LA11_0 < 42 || LA11_0 > 44) && LA11_0 != 46 && LA11_0 != 48 && (LA11_0 < 51 || LA11_0 > 56) && LA11_0 != 60 && (LA11_0 < 62 || LA11_0 > 66) && (LA11_0 < 68 || LA11_0 > 69) && (LA11_0 < 71 || LA11_0 > 72) && (LA11_0 < 76 || LA11_0 > 77) && (LA11_0 < 79 || LA11_0 > 83) && LA11_0 != 89 && (LA11_0 < 181 || LA11_0 > 182) && (LA11_0 < 187 || LA11_0 > 189) && LA11_0 != 191 && LA11_0 != 195 && (LA11_0 < 201 || LA11_0 > 202)) {
//                    if (LA11_0 != 179) {
//                        NoViableAltException nvae = new NoViableAltException("", 11, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt11 = 2;
//                } else {
//                    alt11 = 1;
//                }
//
//                switch(alt11) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_simple_name_in_member_name2268);
//                        simple_name87 = this.simple_name();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, simple_name87.getTree());
//                        break;
//                    case 2:
//                        MEMBER_NAME88 = (Token)this.match(this.input, 179, FOLLOW_MEMBER_NAME_in_member_name2274);
//                        stream_MEMBER_NAME.add(MEMBER_NAME88);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(195, MEMBER_NAME88));
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.method_prototype_return method_prototype() throws RecognitionException {
//        smaliParser.method_prototype_return retval = new smaliParser.method_prototype_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token OPEN_PAREN89 = null;
//        Token CLOSE_PAREN91 = null;
//        ParserRuleReturnScope param_list90 = null;
//        ParserRuleReturnScope type_descriptor92 = null;
//        CommonTree OPEN_PAREN89_tree = null;
//        CommonTree CLOSE_PAREN91_tree = null;
//        RewriteRuleTokenStream stream_OPEN_PAREN = new RewriteRuleTokenStream(this.adaptor, "token OPEN_PAREN");
//        RewriteRuleTokenStream stream_CLOSE_PAREN = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_PAREN");
//        RewriteRuleSubtreeStream stream_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule type_descriptor");
//        RewriteRuleSubtreeStream stream_param_list = new RewriteRuleSubtreeStream(this.adaptor, "rule param_list");
//
//        try {
//            try {
//                OPEN_PAREN89 = (Token)this.match(this.input, 184, FOLLOW_OPEN_PAREN_in_method_prototype2289);
//                stream_OPEN_PAREN.add(OPEN_PAREN89);
//                this.pushFollow(FOLLOW_param_list_in_method_prototype2291);
//                param_list90 = this.param_list();
//                --this.state._fsp;
//                stream_param_list.add(param_list90.getTree());
//                CLOSE_PAREN91 = (Token)this.match(this.input, 18, FOLLOW_CLOSE_PAREN_in_method_prototype2293);
//                stream_CLOSE_PAREN.add(CLOSE_PAREN91);
//                this.pushFollow(FOLLOW_type_descriptor_in_method_prototype2295);
//                type_descriptor92 = this.type_descriptor();
//                --this.state._fsp;
//                stream_type_descriptor.add(type_descriptor92.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(119, retval.start, "I_METHOD_PROTOTYPE"), root_1);
//                CommonTree root_2 = (CommonTree)this.adaptor.nil();
//                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(120, "I_METHOD_RETURN_TYPE"), root_2);
//                this.adaptor.addChild(root_2, stream_type_descriptor.nextTree());
//                this.adaptor.addChild(root_1, root_2);
//                if (stream_param_list.hasNext()) {
//                    this.adaptor.addChild(root_1, stream_param_list.nextTree());
//                }
//
//                stream_param_list.reset();
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.param_list_or_id_primitive_type_return param_list_or_id_primitive_type() throws RecognitionException {
//        smaliParser.param_list_or_id_primitive_type_return retval = new smaliParser.param_list_or_id_primitive_type_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token PARAM_LIST_OR_ID_PRIMITIVE_TYPE93 = null;
//        CommonTree PARAM_LIST_OR_ID_PRIMITIVE_TYPE93_tree = null;
//        RewriteRuleTokenStream stream_PARAM_LIST_OR_ID_PRIMITIVE_TYPE = new RewriteRuleTokenStream(this.adaptor, "token PARAM_LIST_OR_ID_PRIMITIVE_TYPE");
//
//        try {
//            try {
//                PARAM_LIST_OR_ID_PRIMITIVE_TYPE93 = (Token)this.match(this.input, 187, FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id_primitive_type2325);
//                stream_PARAM_LIST_OR_ID_PRIMITIVE_TYPE.add(PARAM_LIST_OR_ID_PRIMITIVE_TYPE93);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(189, PARAM_LIST_OR_ID_PRIMITIVE_TYPE93));
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var10) {
//                this.reportError(var10);
//                this.recover(this.input, var10);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var10);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.param_list_return param_list() throws RecognitionException {
//        smaliParser.param_list_return retval = new smaliParser.param_list_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        ParserRuleReturnScope param_list_or_id_primitive_type94 = null;
//        smaliParser.nonvoid_type_descriptor_return nonvoid_type_descriptor95 = null;
//
//        try {
//            try {
//                int LA14_0 = this.input.LA(1);
//                byte alt14;
//                if (LA14_0 == 187) {
//                    alt14 = 1;
//                } else {
//                    if (LA14_0 != 8 && LA14_0 != 15 && LA14_0 != 18 && LA14_0 != 189) {
//                        NoViableAltException nvae = new NoViableAltException("", 14, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt14 = 2;
//                }
//
//                label125:
//                switch(alt14) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        int cnt12 = 0;
//
//                        while(true) {
//                            int alt12 = 2;
//                            int LA12_0 = this.input.LA(1);
//                            if (LA12_0 == 187) {
//                                alt12 = 1;
//                            }
//
//                            switch(alt12) {
//                                case 1:
//                                    this.pushFollow(FOLLOW_param_list_or_id_primitive_type_in_param_list2340);
//                                    param_list_or_id_primitive_type94 = this.param_list_or_id_primitive_type();
//                                    --this.state._fsp;
//                                    this.adaptor.addChild(root_0, param_list_or_id_primitive_type94.getTree());
//                                    ++cnt12;
//                                    break;
//                                default:
//                                    if (cnt12 < 1) {
//                                        EarlyExitException eee = new EarlyExitException(12, this.input);
//                                        throw eee;
//                                    }
//                                    break label125;
//                            }
//                        }
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//
//                        label134:
//                        while(true) {
//                            int alt13 = 2;
//                            int LA13_0 = this.input.LA(1);
//                            if (LA13_0 == 8 || LA13_0 == 15 || LA13_0 == 189) {
//                                alt13 = 1;
//                            }
//
//                            switch(alt13) {
//                                case 1:
//                                    this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_param_list2347);
//                                    nonvoid_type_descriptor95 = this.nonvoid_type_descriptor();
//                                    --this.state._fsp;
//                                    this.adaptor.addChild(root_0, nonvoid_type_descriptor95.getTree());
//                                    break;
//                                default:
//                                    break label134;
//                            }
//                        }
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.array_descriptor_return array_descriptor() throws RecognitionException {
//        smaliParser.array_descriptor_return retval = new smaliParser.array_descriptor_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ARRAY_TYPE_PREFIX96 = null;
//        Token set97 = null;
//        CommonTree ARRAY_TYPE_PREFIX96_tree = null;
//        Object var6 = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                ARRAY_TYPE_PREFIX96 = (Token)this.match(this.input, 8, FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor2358);
//                ARRAY_TYPE_PREFIX96_tree = (CommonTree)this.adaptor.create(ARRAY_TYPE_PREFIX96);
//                this.adaptor.addChild(root_0, ARRAY_TYPE_PREFIX96_tree);
//                set97 = this.input.LT(1);
//                if (this.input.LA(1) != 15 && this.input.LA(1) != 189) {
//                    MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
//                    throw mse;
//                }
//
//                this.input.consume();
//                this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(set97));
//                this.state.errorRecovery = false;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var11) {
//                this.reportError(var11);
//                this.recover(this.input, var11);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.type_descriptor_return type_descriptor() throws RecognitionException {
//        smaliParser.type_descriptor_return retval = new smaliParser.type_descriptor_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token VOID_TYPE98 = null;
//        Token PRIMITIVE_TYPE99 = null;
//        Token CLASS_DESCRIPTOR100 = null;
//        ParserRuleReturnScope array_descriptor101 = null;
//        CommonTree VOID_TYPE98_tree = null;
//        CommonTree PRIMITIVE_TYPE99_tree = null;
//        CommonTree CLASS_DESCRIPTOR100_tree = null;
//
//        try {
//            try {
//                byte alt15;
//                switch(this.input.LA(1)) {
//                    case 8:
//                        alt15 = 4;
//                        break;
//                    case 15:
//                        alt15 = 3;
//                        break;
//                    case 189:
//                        alt15 = 2;
//                        break;
//                    case 202:
//                        alt15 = 1;
//                        break;
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 15, 0, this.input);
//                        throw nvae;
//                }
//
//                switch(alt15) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        VOID_TYPE98 = (Token)this.match(this.input, 202, FOLLOW_VOID_TYPE_in_type_descriptor2376);
//                        VOID_TYPE98_tree = (CommonTree)this.adaptor.create(VOID_TYPE98);
//                        this.adaptor.addChild(root_0, VOID_TYPE98_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        PRIMITIVE_TYPE99 = (Token)this.match(this.input, 189, FOLLOW_PRIMITIVE_TYPE_in_type_descriptor2382);
//                        PRIMITIVE_TYPE99_tree = (CommonTree)this.adaptor.create(PRIMITIVE_TYPE99);
//                        this.adaptor.addChild(root_0, PRIMITIVE_TYPE99_tree);
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CLASS_DESCRIPTOR100 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_type_descriptor2388);
//                        CLASS_DESCRIPTOR100_tree = (CommonTree)this.adaptor.create(CLASS_DESCRIPTOR100);
//                        this.adaptor.addChild(root_0, CLASS_DESCRIPTOR100_tree);
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_array_descriptor_in_type_descriptor2394);
//                        array_descriptor101 = this.array_descriptor();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, array_descriptor101.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var15) {
//                this.reportError(var15);
//                this.recover(this.input, var15);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var15);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.nonvoid_type_descriptor_return nonvoid_type_descriptor() throws RecognitionException {
//        smaliParser.nonvoid_type_descriptor_return retval = new smaliParser.nonvoid_type_descriptor_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token PRIMITIVE_TYPE102 = null;
//        Token CLASS_DESCRIPTOR103 = null;
//        ParserRuleReturnScope array_descriptor104 = null;
//        CommonTree PRIMITIVE_TYPE102_tree = null;
//        CommonTree CLASS_DESCRIPTOR103_tree = null;
//
//        try {
//            try {
//                byte alt16;
//                switch(this.input.LA(1)) {
//                    case 8:
//                        alt16 = 3;
//                        break;
//                    case 15:
//                        alt16 = 2;
//                        break;
//                    case 189:
//                        alt16 = 1;
//                        break;
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 16, 0, this.input);
//                        throw nvae;
//                }
//
//                switch(alt16) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        PRIMITIVE_TYPE102 = (Token)this.match(this.input, 189, FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor2404);
//                        PRIMITIVE_TYPE102_tree = (CommonTree)this.adaptor.create(PRIMITIVE_TYPE102);
//                        this.adaptor.addChild(root_0, PRIMITIVE_TYPE102_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CLASS_DESCRIPTOR103 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor2410);
//                        CLASS_DESCRIPTOR103_tree = (CommonTree)this.adaptor.create(CLASS_DESCRIPTOR103);
//                        this.adaptor.addChild(root_0, CLASS_DESCRIPTOR103_tree);
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_array_descriptor_in_nonvoid_type_descriptor2416);
//                        array_descriptor104 = this.array_descriptor();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, array_descriptor104.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.reference_type_descriptor_return reference_type_descriptor() throws RecognitionException {
//        smaliParser.reference_type_descriptor_return retval = new smaliParser.reference_type_descriptor_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token CLASS_DESCRIPTOR105 = null;
//        ParserRuleReturnScope array_descriptor106 = null;
//        CommonTree CLASS_DESCRIPTOR105_tree = null;
//
//        try {
//            try {
//                int LA17_0 = this.input.LA(1);
//                byte alt17;
//                if (LA17_0 == 15) {
//                    alt17 = 1;
//                } else {
//                    if (LA17_0 != 8) {
//                        NoViableAltException nvae = new NoViableAltException("", 17, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt17 = 2;
//                }
//
//                switch(alt17) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CLASS_DESCRIPTOR105 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor2426);
//                        CLASS_DESCRIPTOR105_tree = (CommonTree)this.adaptor.create(CLASS_DESCRIPTOR105);
//                        this.adaptor.addChild(root_0, CLASS_DESCRIPTOR105_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_array_descriptor_in_reference_type_descriptor2432);
//                        array_descriptor106 = this.array_descriptor();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, array_descriptor106.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var12) {
//                this.reportError(var12);
//                this.recover(this.input, var12);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var12);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.integer_literal_return integer_literal() throws RecognitionException {
//        smaliParser.integer_literal_return retval = new smaliParser.integer_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token POSITIVE_INTEGER_LITERAL107 = null;
//        Token NEGATIVE_INTEGER_LITERAL108 = null;
//        CommonTree POSITIVE_INTEGER_LITERAL107_tree = null;
//        CommonTree NEGATIVE_INTEGER_LITERAL108_tree = null;
//        RewriteRuleTokenStream stream_NEGATIVE_INTEGER_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token NEGATIVE_INTEGER_LITERAL");
//        RewriteRuleTokenStream stream_POSITIVE_INTEGER_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token POSITIVE_INTEGER_LITERAL");
//
//        try {
//            try {
//                int LA18_0 = this.input.LA(1);
//                byte alt18;
//                if (LA18_0 == 188) {
//                    alt18 = 1;
//                } else {
//                    if (LA18_0 != 181) {
//                        NoViableAltException nvae = new NoViableAltException("", 18, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt18 = 2;
//                }
//
//                switch(alt18) {
//                    case 1:
//                        POSITIVE_INTEGER_LITERAL107 = (Token)this.match(this.input, 188, FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2442);
//                        stream_POSITIVE_INTEGER_LITERAL.add(POSITIVE_INTEGER_LITERAL107);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(90, POSITIVE_INTEGER_LITERAL107));
//                        retval.tree = root_0;
//                        break;
//                    case 2:
//                        NEGATIVE_INTEGER_LITERAL108 = (Token)this.match(this.input, 181, FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2453);
//                        stream_NEGATIVE_INTEGER_LITERAL.add(NEGATIVE_INTEGER_LITERAL108);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(90, NEGATIVE_INTEGER_LITERAL108));
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var15) {
//                this.reportError(var15);
//                this.recover(this.input, var15);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var15);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.float_literal_return float_literal() throws RecognitionException {
//        smaliParser.float_literal_return retval = new smaliParser.float_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token FLOAT_LITERAL_OR_ID109 = null;
//        Token FLOAT_LITERAL110 = null;
//        CommonTree FLOAT_LITERAL_OR_ID109_tree = null;
//        CommonTree FLOAT_LITERAL110_tree = null;
//        RewriteRuleTokenStream stream_FLOAT_LITERAL_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token FLOAT_LITERAL_OR_ID");
//
//        try {
//            try {
//                int LA19_0 = this.input.LA(1);
//                byte alt19;
//                if (LA19_0 == 39) {
//                    alt19 = 1;
//                } else {
//                    if (LA19_0 != 38) {
//                        NoViableAltException nvae = new NoViableAltException("", 19, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt19 = 2;
//                }
//
//                switch(alt19) {
//                    case 1:
//                        FLOAT_LITERAL_OR_ID109 = (Token)this.match(this.input, 39, FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2468);
//                        stream_FLOAT_LITERAL_OR_ID.add(FLOAT_LITERAL_OR_ID109);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(38, FLOAT_LITERAL_OR_ID109));
//                        retval.tree = root_0;
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        FLOAT_LITERAL110 = (Token)this.match(this.input, 38, FOLLOW_FLOAT_LITERAL_in_float_literal2479);
//                        FLOAT_LITERAL110_tree = (CommonTree)this.adaptor.create(FLOAT_LITERAL110);
//                        this.adaptor.addChild(root_0, FLOAT_LITERAL110_tree);
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.double_literal_return double_literal() throws RecognitionException {
//        smaliParser.double_literal_return retval = new smaliParser.double_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token DOUBLE_LITERAL_OR_ID111 = null;
//        Token DOUBLE_LITERAL112 = null;
//        CommonTree DOUBLE_LITERAL_OR_ID111_tree = null;
//        CommonTree DOUBLE_LITERAL112_tree = null;
//        RewriteRuleTokenStream stream_DOUBLE_LITERAL_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token DOUBLE_LITERAL_OR_ID");
//
//        try {
//            try {
//                int LA20_0 = this.input.LA(1);
//                byte alt20;
//                if (LA20_0 == 23) {
//                    alt20 = 1;
//                } else {
//                    if (LA20_0 != 22) {
//                        NoViableAltException nvae = new NoViableAltException("", 20, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt20 = 2;
//                }
//
//                switch(alt20) {
//                    case 1:
//                        DOUBLE_LITERAL_OR_ID111 = (Token)this.match(this.input, 23, FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2489);
//                        stream_DOUBLE_LITERAL_OR_ID.add(DOUBLE_LITERAL_OR_ID111);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(22, DOUBLE_LITERAL_OR_ID111));
//                        retval.tree = root_0;
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        DOUBLE_LITERAL112 = (Token)this.match(this.input, 22, FOLLOW_DOUBLE_LITERAL_in_double_literal2500);
//                        DOUBLE_LITERAL112_tree = (CommonTree)this.adaptor.create(DOUBLE_LITERAL112);
//                        this.adaptor.addChild(root_0, DOUBLE_LITERAL112_tree);
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.literal_return literal() throws RecognitionException {
//        smaliParser.literal_return retval = new smaliParser.literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token LONG_LITERAL113 = null;
//        Token SHORT_LITERAL115 = null;
//        Token BYTE_LITERAL116 = null;
//        Token CHAR_LITERAL119 = null;
//        Token STRING_LITERAL120 = null;
//        Token BOOL_LITERAL121 = null;
//        Token NULL_LITERAL122 = null;
//        ParserRuleReturnScope integer_literal114 = null;
//        ParserRuleReturnScope float_literal117 = null;
//        ParserRuleReturnScope double_literal118 = null;
//        ParserRuleReturnScope array_literal123 = null;
//        ParserRuleReturnScope subannotation124 = null;
//        ParserRuleReturnScope type_field_method_literal125 = null;
//        ParserRuleReturnScope enum_literal126 = null;
//        CommonTree LONG_LITERAL113_tree = null;
//        CommonTree SHORT_LITERAL115_tree = null;
//        CommonTree BYTE_LITERAL116_tree = null;
//        CommonTree CHAR_LITERAL119_tree = null;
//        CommonTree STRING_LITERAL120_tree = null;
//        CommonTree BOOL_LITERAL121_tree = null;
//        CommonTree NULL_LITERAL122_tree = null;
//
//        try {
//            try {
//                int LA21_2;
//                int nvaeMark;
//                NoViableAltException nvae;
//                byte alt21;
//                switch(this.input.LA(1)) {
//                    case 4:
//                    case 6:
//                    case 8:
//                    case 15:
//                    case 42:
//                    case 43:
//                    case 44:
//                    case 46:
//                    case 48:
//                    case 51:
//                    case 52:
//                    case 53:
//                    case 54:
//                    case 55:
//                    case 56:
//                    case 60:
//                    case 62:
//                    case 63:
//                    case 64:
//                    case 65:
//                    case 66:
//                    case 68:
//                    case 69:
//                    case 71:
//                    case 72:
//                    case 76:
//                    case 77:
//                    case 79:
//                    case 80:
//                    case 81:
//                    case 82:
//                    case 83:
//                    case 89:
//                    case 179:
//                    case 187:
//                    case 189:
//                    case 191:
//                    case 195:
//                    case 201:
//                    case 202:
//                        alt21 = 13;
//                        break;
//                    case 5:
//                    case 7:
//                    case 9:
//                    case 12:
//                    case 13:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 19:
//                    case 20:
//                    case 21:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 27:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 34:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 40:
//                    case 41:
//                    case 45:
//                    case 47:
//                    case 49:
//                    case 50:
//                    case 57:
//                    case 58:
//                    case 59:
//                    case 61:
//                    case 67:
//                    case 70:
//                    case 73:
//                    case 74:
//                    case 75:
//                    case 78:
//                    case 84:
//                    case 85:
//                    case 86:
//                    case 87:
//                    case 88:
//                    case 90:
//                    case 91:
//                    case 92:
//                    case 93:
//                    case 94:
//                    case 95:
//                    case 96:
//                    case 97:
//                    case 98:
//                    case 99:
//                    case 100:
//                    case 101:
//                    case 102:
//                    case 103:
//                    case 104:
//                    case 105:
//                    case 106:
//                    case 107:
//                    case 108:
//                    case 109:
//                    case 110:
//                    case 111:
//                    case 112:
//                    case 113:
//                    case 114:
//                    case 115:
//                    case 116:
//                    case 117:
//                    case 118:
//                    case 119:
//                    case 120:
//                    case 121:
//                    case 122:
//                    case 123:
//                    case 124:
//                    case 125:
//                    case 126:
//                    case 127:
//                    case 128:
//                    case 129:
//                    case 130:
//                    case 131:
//                    case 132:
//                    case 133:
//                    case 134:
//                    case 135:
//                    case 136:
//                    case 137:
//                    case 138:
//                    case 139:
//                    case 140:
//                    case 141:
//                    case 142:
//                    case 143:
//                    case 144:
//                    case 145:
//                    case 146:
//                    case 147:
//                    case 148:
//                    case 149:
//                    case 150:
//                    case 151:
//                    case 152:
//                    case 153:
//                    case 154:
//                    case 155:
//                    case 156:
//                    case 157:
//                    case 158:
//                    case 159:
//                    case 160:
//                    case 161:
//                    case 162:
//                    case 163:
//                    case 164:
//                    case 165:
//                    case 166:
//                    case 167:
//                    case 168:
//                    case 169:
//                    case 170:
//                    case 171:
//                    case 172:
//                    case 173:
//                    case 174:
//                    case 175:
//                    case 176:
//                    case 177:
//                    case 180:
//                    case 184:
//                    case 185:
//                    case 186:
//                    case 190:
//                    case 192:
//                    case 193:
//                    case 196:
//                    case 197:
//                    case 200:
//                    default:
//
//                        throw new NoViableAltException("", 21, 0, this.input);
//                    case 10:
//                        LA21_2 = this.input.LA(2);
//                        if (LA21_2 == -1 || LA21_2 >= 4 && LA21_2 <= 6 || LA21_2 == 10 || LA21_2 >= 16 && LA21_2 <= 17 || LA21_2 == 20 || LA21_2 >= 23 && LA21_2 <= 24 || LA21_2 == 26 || LA21_2 == 32 || LA21_2 == 36 || LA21_2 >= 39 && LA21_2 <= 40 || LA21_2 >= 42 && LA21_2 <= 44 || LA21_2 == 46 || LA21_2 == 48 || LA21_2 >= 51 && LA21_2 <= 56 || LA21_2 == 60 || LA21_2 >= 62 && LA21_2 <= 66 || LA21_2 >= 68 && LA21_2 <= 69 || LA21_2 >= 71 && LA21_2 <= 72 || LA21_2 >= 76 && LA21_2 <= 77 || LA21_2 >= 79 && LA21_2 <= 83 || LA21_2 == 89 || LA21_2 >= 180 && LA21_2 <= 182 || LA21_2 >= 187 && LA21_2 <= 189 || LA21_2 == 191 || LA21_2 >= 195 && LA21_2 <= 196 || LA21_2 >= 200 && LA21_2 <= 202) {
//                            alt21 = 9;
//                        } else {
//                            if (LA21_2 != 19 && LA21_2 != 184) {
//                                nvaeMark = this.input.mark();
//
//                                try {
//                                    this.input.consume();
//                                    nvae = new NoViableAltException("", 21, 12, this.input);
//                                    throw nvae;
//                                } finally {
//                                    this.input.rewind(nvaeMark);
//                                }
//                            }
//
//                            alt21 = 13;
//                        }
//                        break;
//                    case 11:
//                        alt21 = 4;
//                        break;
//                    case 14:
//                        alt21 = 7;
//                        break;
//                    case 22:
//                        alt21 = 6;
//                        break;
//                    case 23:
//                        LA21_2 = this.input.LA(2);
//                        if (LA21_2 != -1 && (LA21_2 < 4 || LA21_2 > 6) && LA21_2 != 10 && (LA21_2 < 16 || LA21_2 > 17) && LA21_2 != 20 && (LA21_2 < 23 || LA21_2 > 24) && LA21_2 != 26 && LA21_2 != 32 && LA21_2 != 36 && (LA21_2 < 39 || LA21_2 > 40) && (LA21_2 < 42 || LA21_2 > 44) && LA21_2 != 46 && LA21_2 != 48 && (LA21_2 < 51 || LA21_2 > 56) && LA21_2 != 60 && (LA21_2 < 62 || LA21_2 > 66) && (LA21_2 < 68 || LA21_2 > 69) && (LA21_2 < 71 || LA21_2 > 72) && (LA21_2 < 76 || LA21_2 > 77) && (LA21_2 < 79 || LA21_2 > 83) && LA21_2 != 89 && (LA21_2 < 180 || LA21_2 > 182) && (LA21_2 < 187 || LA21_2 > 189) && LA21_2 != 191 && (LA21_2 < 195 || LA21_2 > 196) && (LA21_2 < 200 || LA21_2 > 202)) {
//                            if (LA21_2 != 19 && LA21_2 != 184) {
//                                nvaeMark = this.input.mark();
//
//                                try {
//                                    this.input.consume();
//                                    nvae = new NoViableAltException("", 21, 8, this.input);
//                                    throw nvae;
//                                } finally {
//                                    this.input.rewind(nvaeMark);
//                                }
//                            }
//
//                            alt21 = 13;
//                            break;
//                        }
//
//                        alt21 = 6;
//                        break;
//                    case 33:
//                        alt21 = 14;
//                        break;
//                    case 38:
//                        alt21 = 5;
//                        break;
//                    case 39:
//                        LA21_2 = this.input.LA(2);
//                        if (LA21_2 != -1 && (LA21_2 < 4 || LA21_2 > 6) && LA21_2 != 10 && (LA21_2 < 16 || LA21_2 > 17) && LA21_2 != 20 && (LA21_2 < 23 || LA21_2 > 24) && LA21_2 != 26 && LA21_2 != 32 && LA21_2 != 36 && (LA21_2 < 39 || LA21_2 > 40) && (LA21_2 < 42 || LA21_2 > 44) && LA21_2 != 46 && LA21_2 != 48 && (LA21_2 < 51 || LA21_2 > 56) && LA21_2 != 60 && (LA21_2 < 62 || LA21_2 > 66) && (LA21_2 < 68 || LA21_2 > 69) && (LA21_2 < 71 || LA21_2 > 72) && (LA21_2 < 76 || LA21_2 > 77) && (LA21_2 < 79 || LA21_2 > 83) && LA21_2 != 89 && (LA21_2 < 180 || LA21_2 > 182) && (LA21_2 < 187 || LA21_2 > 189) && LA21_2 != 191 && (LA21_2 < 195 || LA21_2 > 196) && (LA21_2 < 200 || LA21_2 > 202)) {
//                            if (LA21_2 != 19 && LA21_2 != 184) {
//                                nvaeMark = this.input.mark();
//
//                                try {
//                                    this.input.consume();
//                                    nvae = new NoViableAltException("", 21, 6, this.input);
//                                    throw nvae;
//                                } finally {
//                                    this.input.rewind(nvaeMark);
//                                }
//                            }
//
//                            alt21 = 13;
//                            break;
//                        }
//
//                        alt21 = 5;
//                        break;
//                    case 178:
//                        alt21 = 1;
//                        break;
//                    case 181:
//                        LA21_2 = this.input.LA(2);
//                        if (LA21_2 == -1 || LA21_2 >= 4 && LA21_2 <= 6 || LA21_2 == 10 || LA21_2 >= 16 && LA21_2 <= 17 || LA21_2 == 20 || LA21_2 >= 23 && LA21_2 <= 24 || LA21_2 == 26 || LA21_2 == 32 || LA21_2 == 36 || LA21_2 >= 39 && LA21_2 <= 40 || LA21_2 >= 42 && LA21_2 <= 44 || LA21_2 == 46 || LA21_2 == 48 || LA21_2 >= 51 && LA21_2 <= 56 || LA21_2 == 60 || LA21_2 >= 62 && LA21_2 <= 66 || LA21_2 >= 68 && LA21_2 <= 69 || LA21_2 >= 71 && LA21_2 <= 72 || LA21_2 >= 76 && LA21_2 <= 77 || LA21_2 >= 79 && LA21_2 <= 83 || LA21_2 == 89 || LA21_2 >= 180 && LA21_2 <= 182 || LA21_2 >= 187 && LA21_2 <= 189 || LA21_2 == 191 || LA21_2 >= 195 && LA21_2 <= 196 || LA21_2 >= 200 && LA21_2 <= 202) {
//                            alt21 = 2;
//                            break;
//                        }
//
//                        if (LA21_2 != 19 && LA21_2 != 184) {
//                            nvaeMark = this.input.mark();
//
//                            try {
//                                this.input.consume();
//                                nvae = new NoViableAltException("", 21, 3, this.input);
//                                throw nvae;
//                            } finally {
//                                this.input.rewind(nvaeMark);
//                            }
//                        }
//
//                        alt21 = 13;
//                        break;
//                    case 182:
//                        LA21_2 = this.input.LA(2);
//                        if (LA21_2 == -1 || LA21_2 >= 4 && LA21_2 <= 6 || LA21_2 == 10 || LA21_2 >= 16 && LA21_2 <= 17 || LA21_2 == 20 || LA21_2 >= 23 && LA21_2 <= 24 || LA21_2 == 26 || LA21_2 == 32 || LA21_2 == 36 || LA21_2 >= 39 && LA21_2 <= 40 || LA21_2 >= 42 && LA21_2 <= 44 || LA21_2 == 46 || LA21_2 == 48 || LA21_2 >= 51 && LA21_2 <= 56 || LA21_2 == 60 || LA21_2 >= 62 && LA21_2 <= 66 || LA21_2 >= 68 && LA21_2 <= 69 || LA21_2 >= 71 && LA21_2 <= 72 || LA21_2 >= 76 && LA21_2 <= 77 || LA21_2 >= 79 && LA21_2 <= 83 || LA21_2 == 89 || LA21_2 >= 180 && LA21_2 <= 182 || LA21_2 >= 187 && LA21_2 <= 189 || LA21_2 == 191 || LA21_2 >= 195 && LA21_2 <= 196 || LA21_2 >= 200 && LA21_2 <= 202) {
//                            alt21 = 10;
//                            break;
//                        }
//
//                        if (LA21_2 != 19 && LA21_2 != 184) {
//                            nvaeMark = this.input.mark();
//
//                            try {
//                                this.input.consume();
//                                nvae = new NoViableAltException("", 21, 13, this.input);
//                                throw nvae;
//                            } finally {
//                                this.input.rewind(nvaeMark);
//                            }
//                        }
//
//                        alt21 = 13;
//                        break;
//                    case 183:
//                        alt21 = 11;
//                        break;
//                    case 188:
//                        LA21_2 = this.input.LA(2);
//                        if (LA21_2 == -1 || LA21_2 >= 4 && LA21_2 <= 6 || LA21_2 == 10 || LA21_2 >= 16 && LA21_2 <= 17 || LA21_2 == 20 || LA21_2 >= 23 && LA21_2 <= 24 || LA21_2 == 26 || LA21_2 == 32 || LA21_2 == 36 || LA21_2 >= 39 && LA21_2 <= 40 || LA21_2 >= 42 && LA21_2 <= 44 || LA21_2 == 46 || LA21_2 == 48 || LA21_2 >= 51 && LA21_2 <= 56 || LA21_2 == 60 || LA21_2 >= 62 && LA21_2 <= 66 || LA21_2 >= 68 && LA21_2 <= 69 || LA21_2 >= 71 && LA21_2 <= 72 || LA21_2 >= 76 && LA21_2 <= 77 || LA21_2 >= 79 && LA21_2 <= 83 || LA21_2 == 89 || LA21_2 >= 180 && LA21_2 <= 182 || LA21_2 >= 187 && LA21_2 <= 189 || LA21_2 == 191 || LA21_2 >= 195 && LA21_2 <= 196 || LA21_2 >= 200 && LA21_2 <= 202) {
//                            alt21 = 2;
//                            break;
//                        }
//
//                        if (LA21_2 != 19 && LA21_2 != 184) {
//                            nvaeMark = this.input.mark();
//
//                            try {
//                                this.input.consume();
//                                nvae = new NoViableAltException("", 21, 2, this.input);
//                                throw nvae;
//                            } finally {
//                                this.input.rewind(nvaeMark);
//                            }
//                        }
//
//                        alt21 = 13;
//                        break;
//                    case 194:
//                        alt21 = 3;
//                        break;
//                    case 198:
//                        alt21 = 8;
//                        break;
//                    case 199:
//                        alt21 = 12;
//                }
//
//                switch(alt21) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        LONG_LITERAL113 = (Token)this.match(this.input, 178, FOLLOW_LONG_LITERAL_in_literal2510);
//                        LONG_LITERAL113_tree = (CommonTree)this.adaptor.create(LONG_LITERAL113);
//                        this.adaptor.addChild(root_0, LONG_LITERAL113_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_integer_literal_in_literal2516);
//                        integer_literal114 = this.integer_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, integer_literal114.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        SHORT_LITERAL115 = (Token)this.match(this.input, 194, FOLLOW_SHORT_LITERAL_in_literal2522);
//                        SHORT_LITERAL115_tree = (CommonTree)this.adaptor.create(SHORT_LITERAL115);
//                        this.adaptor.addChild(root_0, SHORT_LITERAL115_tree);
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BYTE_LITERAL116 = (Token)this.match(this.input, 11, FOLLOW_BYTE_LITERAL_in_literal2528);
//                        BYTE_LITERAL116_tree = (CommonTree)this.adaptor.create(BYTE_LITERAL116);
//                        this.adaptor.addChild(root_0, BYTE_LITERAL116_tree);
//                        break;
//                    case 5:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_float_literal_in_literal2534);
//                        float_literal117 = this.float_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, float_literal117.getTree());
//                        break;
//                    case 6:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_double_literal_in_literal2540);
//                        double_literal118 = this.double_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, double_literal118.getTree());
//                        break;
//                    case 7:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CHAR_LITERAL119 = (Token)this.match(this.input, 14, FOLLOW_CHAR_LITERAL_in_literal2546);
//                        CHAR_LITERAL119_tree = (CommonTree)this.adaptor.create(CHAR_LITERAL119);
//                        this.adaptor.addChild(root_0, CHAR_LITERAL119_tree);
//                        break;
//                    case 8:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        STRING_LITERAL120 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_literal2552);
//                        STRING_LITERAL120_tree = (CommonTree)this.adaptor.create(STRING_LITERAL120);
//                        this.adaptor.addChild(root_0, STRING_LITERAL120_tree);
//                        break;
//                    case 9:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BOOL_LITERAL121 = (Token)this.match(this.input, 10, FOLLOW_BOOL_LITERAL_in_literal2558);
//                        BOOL_LITERAL121_tree = (CommonTree)this.adaptor.create(BOOL_LITERAL121);
//                        this.adaptor.addChild(root_0, BOOL_LITERAL121_tree);
//                        break;
//                    case 10:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        NULL_LITERAL122 = (Token)this.match(this.input, 182, FOLLOW_NULL_LITERAL_in_literal2564);
//                        NULL_LITERAL122_tree = (CommonTree)this.adaptor.create(NULL_LITERAL122);
//                        this.adaptor.addChild(root_0, NULL_LITERAL122_tree);
//                        break;
//                    case 11:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_array_literal_in_literal2570);
//                        array_literal123 = this.array_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, array_literal123.getTree());
//                        break;
//                    case 12:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_subannotation_in_literal2576);
//                        subannotation124 = this.subannotation();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, subannotation124.getTree());
//                        break;
//                    case 13:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_type_field_method_literal_in_literal2582);
//                        type_field_method_literal125 = this.type_field_method_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, type_field_method_literal125.getTree());
//                        break;
//                    case 14:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_enum_literal_in_literal2588);
//                        enum_literal126 = this.enum_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, enum_literal126.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var97) {
//                this.reportError(var97);
//                this.recover(this.input, var97);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var97);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.parsed_integer_literal_return parsed_integer_literal() throws RecognitionException {
//        smaliParser.parsed_integer_literal_return retval = new smaliParser.parsed_integer_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        smaliParser.integer_literal_return integer_literal127 = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                this.pushFollow(FOLLOW_integer_literal_in_parsed_integer_literal2601);
//                integer_literal127 = this.integer_literal();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, integer_literal127.getTree());
//                retval.value = LiteralTools.parseInt(integer_literal127 != null ? this.input.toString(integer_literal127.start, integer_literal127.stop) : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var8) {
//                this.reportError(var8);
//                this.recover(this.input, var8);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var8);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.integral_literal_return integral_literal() throws RecognitionException {
//        smaliParser.integral_literal_return retval = new smaliParser.integral_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token LONG_LITERAL128 = null;
//        Token SHORT_LITERAL130 = null;
//        Token CHAR_LITERAL131 = null;
//        Token BYTE_LITERAL132 = null;
//        ParserRuleReturnScope integer_literal129 = null;
//        CommonTree LONG_LITERAL128_tree = null;
//        CommonTree SHORT_LITERAL130_tree = null;
//        CommonTree CHAR_LITERAL131_tree = null;
//        CommonTree BYTE_LITERAL132_tree = null;
//
//        try {
//            try {
//                byte alt22;
//                switch(this.input.LA(1)) {
//                    case 11:
//                        alt22 = 5;
//                        break;
//                    case 14:
//                        alt22 = 4;
//                        break;
//                    case 178:
//                        alt22 = 1;
//                        break;
//                    case 181:
//                    case 188:
//                        alt22 = 2;
//                        break;
//                    case 194:
//                        alt22 = 3;
//                        break;
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 22, 0, this.input);
//                        throw nvae;
//                }
//
//                switch(alt22) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        LONG_LITERAL128 = (Token)this.match(this.input, 178, FOLLOW_LONG_LITERAL_in_integral_literal2613);
//                        LONG_LITERAL128_tree = (CommonTree)this.adaptor.create(LONG_LITERAL128);
//                        this.adaptor.addChild(root_0, LONG_LITERAL128_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_integer_literal_in_integral_literal2619);
//                        integer_literal129 = this.integer_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, integer_literal129.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        SHORT_LITERAL130 = (Token)this.match(this.input, 194, FOLLOW_SHORT_LITERAL_in_integral_literal2625);
//                        SHORT_LITERAL130_tree = (CommonTree)this.adaptor.create(SHORT_LITERAL130);
//                        this.adaptor.addChild(root_0, SHORT_LITERAL130_tree);
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CHAR_LITERAL131 = (Token)this.match(this.input, 14, FOLLOW_CHAR_LITERAL_in_integral_literal2631);
//                        CHAR_LITERAL131_tree = (CommonTree)this.adaptor.create(CHAR_LITERAL131);
//                        this.adaptor.addChild(root_0, CHAR_LITERAL131_tree);
//                        break;
//                    case 5:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BYTE_LITERAL132 = (Token)this.match(this.input, 11, FOLLOW_BYTE_LITERAL_in_integral_literal2637);
//                        BYTE_LITERAL132_tree = (CommonTree)this.adaptor.create(BYTE_LITERAL132);
//                        this.adaptor.addChild(root_0, BYTE_LITERAL132_tree);
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var17) {
//                this.reportError(var17);
//                this.recover(this.input, var17);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var17);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.fixed_32bit_literal_return fixed_32bit_literal() throws RecognitionException {
//        smaliParser.fixed_32bit_literal_return retval = new smaliParser.fixed_32bit_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token LONG_LITERAL133 = null;
//        Token SHORT_LITERAL135 = null;
//        Token BYTE_LITERAL136 = null;
//        Token CHAR_LITERAL138 = null;
//        Token BOOL_LITERAL139 = null;
//        ParserRuleReturnScope integer_literal134 = null;
//        ParserRuleReturnScope float_literal137 = null;
//        CommonTree LONG_LITERAL133_tree = null;
//        CommonTree SHORT_LITERAL135_tree = null;
//        CommonTree BYTE_LITERAL136_tree = null;
//        CommonTree CHAR_LITERAL138_tree = null;
//        CommonTree BOOL_LITERAL139_tree = null;
//
//        try {
//            try {
//                byte alt23;
//                switch(this.input.LA(1)) {
//                    case 10:
//                        alt23 = 7;
//                        break;
//                    case 11:
//                        alt23 = 4;
//                        break;
//                    case 14:
//                        alt23 = 6;
//                        break;
//                    case 38:
//                    case 39:
//                        alt23 = 5;
//                        break;
//                    case 178:
//                        alt23 = 1;
//                        break;
//                    case 181:
//                    case 188:
//                        alt23 = 2;
//                        break;
//                    case 194:
//                        alt23 = 3;
//                        break;
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 23, 0, this.input);
//                        throw nvae;
//                }
//
//                switch(alt23) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        LONG_LITERAL133 = (Token)this.match(this.input, 178, FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2647);
//                        LONG_LITERAL133_tree = (CommonTree)this.adaptor.create(LONG_LITERAL133);
//                        this.adaptor.addChild(root_0, LONG_LITERAL133_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_integer_literal_in_fixed_32bit_literal2653);
//                        integer_literal134 = this.integer_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, integer_literal134.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        SHORT_LITERAL135 = (Token)this.match(this.input, 194, FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2659);
//                        SHORT_LITERAL135_tree = (CommonTree)this.adaptor.create(SHORT_LITERAL135);
//                        this.adaptor.addChild(root_0, SHORT_LITERAL135_tree);
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BYTE_LITERAL136 = (Token)this.match(this.input, 11, FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2665);
//                        BYTE_LITERAL136_tree = (CommonTree)this.adaptor.create(BYTE_LITERAL136);
//                        this.adaptor.addChild(root_0, BYTE_LITERAL136_tree);
//                        break;
//                    case 5:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_float_literal_in_fixed_32bit_literal2671);
//                        float_literal137 = this.float_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, float_literal137.getTree());
//                        break;
//                    case 6:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CHAR_LITERAL138 = (Token)this.match(this.input, 14, FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2677);
//                        CHAR_LITERAL138_tree = (CommonTree)this.adaptor.create(CHAR_LITERAL138);
//                        this.adaptor.addChild(root_0, CHAR_LITERAL138_tree);
//                        break;
//                    case 7:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BOOL_LITERAL139 = (Token)this.match(this.input, 10, FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2683);
//                        BOOL_LITERAL139_tree = (CommonTree)this.adaptor.create(BOOL_LITERAL139);
//                        this.adaptor.addChild(root_0, BOOL_LITERAL139_tree);
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var20) {
//                this.reportError(var20);
//                this.recover(this.input, var20);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.fixed_literal_return fixed_literal() throws RecognitionException {
//        smaliParser.fixed_literal_return retval = new smaliParser.fixed_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token LONG_LITERAL141 = null;
//        Token SHORT_LITERAL142 = null;
//        Token BYTE_LITERAL143 = null;
//        Token CHAR_LITERAL146 = null;
//        Token BOOL_LITERAL147 = null;
//        ParserRuleReturnScope integer_literal140 = null;
//        ParserRuleReturnScope float_literal144 = null;
//        ParserRuleReturnScope double_literal145 = null;
//        CommonTree LONG_LITERAL141_tree = null;
//        CommonTree SHORT_LITERAL142_tree = null;
//        CommonTree BYTE_LITERAL143_tree = null;
//        CommonTree CHAR_LITERAL146_tree = null;
//        CommonTree BOOL_LITERAL147_tree = null;
//
//        try {
//            try {
//                byte alt24;
//                switch(this.input.LA(1)) {
//                    case 10:
//                        alt24 = 8;
//                        break;
//                    case 11:
//                        alt24 = 4;
//                        break;
//                    case 14:
//                        alt24 = 7;
//                        break;
//                    case 22:
//                    case 23:
//                        alt24 = 6;
//                        break;
//                    case 38:
//                    case 39:
//                        alt24 = 5;
//                        break;
//                    case 178:
//                        alt24 = 2;
//                        break;
//                    case 181:
//                    case 188:
//                        alt24 = 1;
//                        break;
//                    case 194:
//                        alt24 = 3;
//                        break;
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 24, 0, this.input);
//                        throw nvae;
//                }
//
//                switch(alt24) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_integer_literal_in_fixed_literal2693);
//                        integer_literal140 = this.integer_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, integer_literal140.getTree());
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        LONG_LITERAL141 = (Token)this.match(this.input, 178, FOLLOW_LONG_LITERAL_in_fixed_literal2699);
//                        LONG_LITERAL141_tree = (CommonTree)this.adaptor.create(LONG_LITERAL141);
//                        this.adaptor.addChild(root_0, LONG_LITERAL141_tree);
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        SHORT_LITERAL142 = (Token)this.match(this.input, 194, FOLLOW_SHORT_LITERAL_in_fixed_literal2705);
//                        SHORT_LITERAL142_tree = (CommonTree)this.adaptor.create(SHORT_LITERAL142);
//                        this.adaptor.addChild(root_0, SHORT_LITERAL142_tree);
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BYTE_LITERAL143 = (Token)this.match(this.input, 11, FOLLOW_BYTE_LITERAL_in_fixed_literal2711);
//                        BYTE_LITERAL143_tree = (CommonTree)this.adaptor.create(BYTE_LITERAL143);
//                        this.adaptor.addChild(root_0, BYTE_LITERAL143_tree);
//                        break;
//                    case 5:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_float_literal_in_fixed_literal2717);
//                        float_literal144 = this.float_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, float_literal144.getTree());
//                        break;
//                    case 6:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_double_literal_in_fixed_literal2723);
//                        double_literal145 = this.double_literal();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, double_literal145.getTree());
//                        break;
//                    case 7:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CHAR_LITERAL146 = (Token)this.match(this.input, 14, FOLLOW_CHAR_LITERAL_in_fixed_literal2729);
//                        CHAR_LITERAL146_tree = (CommonTree)this.adaptor.create(CHAR_LITERAL146);
//                        this.adaptor.addChild(root_0, CHAR_LITERAL146_tree);
//                        break;
//                    case 8:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        BOOL_LITERAL147 = (Token)this.match(this.input, 10, FOLLOW_BOOL_LITERAL_in_fixed_literal2735);
//                        BOOL_LITERAL147_tree = (CommonTree)this.adaptor.create(BOOL_LITERAL147);
//                        this.adaptor.addChild(root_0, BOOL_LITERAL147_tree);
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var21) {
//                this.reportError(var21);
//                this.recover(this.input, var21);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var21);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.array_literal_return array_literal() throws RecognitionException {
//        smaliParser.array_literal_return retval = new smaliParser.array_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token OPEN_BRACE148 = null;
//        Token COMMA150 = null;
//        Token CLOSE_BRACE152 = null;
//        ParserRuleReturnScope literal149 = null;
//        ParserRuleReturnScope literal151 = null;
//        CommonTree OPEN_BRACE148_tree = null;
//        CommonTree COMMA150_tree = null;
//        CommonTree CLOSE_BRACE152_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleSubtreeStream stream_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule literal");
//
//        try {
//            try {
//                OPEN_BRACE148 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_array_literal2745);
//                stream_OPEN_BRACE.add(OPEN_BRACE148);
//                int LA26_0 = this.input.LA(1);
//                byte alt26;
//                if (LA26_0 == 4 || LA26_0 == 6 || LA26_0 == 8 || LA26_0 >= 10 && LA26_0 <= 11 || LA26_0 >= 14 && LA26_0 <= 15 || LA26_0 >= 22 && LA26_0 <= 23 || LA26_0 == 33 || LA26_0 >= 38 && LA26_0 <= 39 || LA26_0 >= 42 && LA26_0 <= 44 || LA26_0 == 46 || LA26_0 == 48 || LA26_0 >= 51 && LA26_0 <= 56 || LA26_0 == 60 || LA26_0 >= 62 && LA26_0 <= 66 || LA26_0 >= 68 && LA26_0 <= 69 || LA26_0 >= 71 && LA26_0 <= 72 || LA26_0 >= 76 && LA26_0 <= 77 || LA26_0 >= 79 && LA26_0 <= 83 || LA26_0 == 89 || LA26_0 >= 178 && LA26_0 <= 179 || LA26_0 >= 181 && LA26_0 <= 183 || LA26_0 >= 187 && LA26_0 <= 189 || LA26_0 == 191 || LA26_0 >= 194 && LA26_0 <= 195 || LA26_0 >= 198 && LA26_0 <= 199 || LA26_0 >= 201 && LA26_0 <= 202) {
//                    alt26 = 1;
//                } else {
//                    if (LA26_0 != 17) {
//                        NoViableAltException nvae = new NoViableAltException("", 26, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt26 = 2;
//                }
//
//                switch(alt26) {
//                    case 1:
//                        this.pushFollow(FOLLOW_literal_in_array_literal2748);
//                        literal149 = this.literal();
//                        --this.state._fsp;
//                        stream_literal.add(literal149.getTree());
//
//                        label264:
//                        while(true) {
//                            int alt25 = 2;
//                            int LA25_0 = this.input.LA(1);
//                            if (LA25_0 == 20) {
//                                alt25 = 1;
//                            }
//
//                            switch(alt25) {
//                                case 1:
//                                    COMMA150 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_array_literal2751);
//                                    stream_COMMA.add(COMMA150);
//                                    this.pushFollow(FOLLOW_literal_in_array_literal2753);
//                                    literal151 = this.literal();
//                                    --this.state._fsp;
//                                    stream_literal.add(literal151.getTree());
//                                    break;
//                                default:
//                                    break label264;
//                            }
//                        }
//                    case 2:
//                }
//
//                CLOSE_BRACE152 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_array_literal2761);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE152);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(102, retval.start, "I_ENCODED_ARRAY"), root_1);
//
//                while(stream_literal.hasNext()) {
//                    this.adaptor.addChild(root_1, stream_literal.nextTree());
//                }
//
//                stream_literal.reset();
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var22) {
//                this.reportError(var22);
//                this.recover(this.input, var22);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var22);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.annotation_element_return annotation_element() throws RecognitionException {
//        smaliParser.annotation_element_return retval = new smaliParser.annotation_element_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token EQUAL154 = null;
//        ParserRuleReturnScope simple_name153 = null;
//        ParserRuleReturnScope literal155 = null;
//        CommonTree EQUAL154_tree = null;
//        RewriteRuleTokenStream stream_EQUAL = new RewriteRuleTokenStream(this.adaptor, "token EQUAL");
//        RewriteRuleSubtreeStream stream_simple_name = new RewriteRuleSubtreeStream(this.adaptor, "rule simple_name");
//        RewriteRuleSubtreeStream stream_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule literal");
//
//        try {
//            try {
//                this.pushFollow(FOLLOW_simple_name_in_annotation_element2785);
//                simple_name153 = this.simple_name();
//                --this.state._fsp;
//                stream_simple_name.add(simple_name153.getTree());
//                EQUAL154 = (Token)this.match(this.input, 35, FOLLOW_EQUAL_in_annotation_element2787);
//                stream_EQUAL.add(EQUAL154);
//                this.pushFollow(FOLLOW_literal_in_annotation_element2789);
//                literal155 = this.literal();
//                --this.state._fsp;
//                stream_literal.add(literal155.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(95, retval.start, "I_ANNOTATION_ELEMENT"), root_1);
//                this.adaptor.addChild(root_1, stream_simple_name.nextTree());
//                this.adaptor.addChild(root_1, stream_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var15) {
//                this.reportError(var15);
//                this.recover(this.input, var15);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var15);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.annotation_return annotation() throws RecognitionException {
//        smaliParser.annotation_return retval = new smaliParser.annotation_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ANNOTATION_DIRECTIVE156 = null;
//        Token ANNOTATION_VISIBILITY157 = null;
//        Token CLASS_DESCRIPTOR158 = null;
//        Token END_ANNOTATION_DIRECTIVE160 = null;
//        ParserRuleReturnScope annotation_element159 = null;
//        CommonTree ANNOTATION_DIRECTIVE156_tree = null;
//        CommonTree ANNOTATION_VISIBILITY157_tree = null;
//        CommonTree CLASS_DESCRIPTOR158_tree = null;
//        CommonTree END_ANNOTATION_DIRECTIVE160_tree = null;
//        RewriteRuleTokenStream stream_ANNOTATION_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token ANNOTATION_DIRECTIVE");
//        RewriteRuleTokenStream stream_ANNOTATION_VISIBILITY = new RewriteRuleTokenStream(this.adaptor, "token ANNOTATION_VISIBILITY");
//        RewriteRuleTokenStream stream_CLASS_DESCRIPTOR = new RewriteRuleTokenStream(this.adaptor, "token CLASS_DESCRIPTOR");
//        RewriteRuleTokenStream stream_END_ANNOTATION_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_ANNOTATION_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_annotation_element = new RewriteRuleSubtreeStream(this.adaptor, "rule annotation_element");
//
//        try {
//            ANNOTATION_DIRECTIVE156 = (Token)this.match(this.input, 5, FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2814);
//            stream_ANNOTATION_DIRECTIVE.add(ANNOTATION_DIRECTIVE156);
//            ANNOTATION_VISIBILITY157 = (Token)this.match(this.input, 6, FOLLOW_ANNOTATION_VISIBILITY_in_annotation2816);
//            stream_ANNOTATION_VISIBILITY.add(ANNOTATION_VISIBILITY157);
//            CLASS_DESCRIPTOR158 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_annotation2818);
//            stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR158);
//
//            while(true) {
//                int alt27 = 2;
//                int LA27_0 = this.input.LA(1);
//                if (LA27_0 == 4 || LA27_0 == 6 || LA27_0 == 10 || LA27_0 == 23 || LA27_0 == 39 || LA27_0 >= 42 && LA27_0 <= 44 || LA27_0 == 46 || LA27_0 == 48 || LA27_0 >= 51 && LA27_0 <= 56 || LA27_0 == 60 || LA27_0 >= 62 && LA27_0 <= 66 || LA27_0 >= 68 && LA27_0 <= 69 || LA27_0 >= 71 && LA27_0 <= 72 || LA27_0 >= 76 && LA27_0 <= 77 || LA27_0 >= 79 && LA27_0 <= 83 || LA27_0 == 89 || LA27_0 >= 181 && LA27_0 <= 182 || LA27_0 >= 187 && LA27_0 <= 189 || LA27_0 == 191 || LA27_0 == 195 || LA27_0 >= 201 && LA27_0 <= 202) {
//                    alt27 = 1;
//                }
//
//                switch(alt27) {
//                    case 1:
//                        this.pushFollow(FOLLOW_annotation_element_in_annotation2824);
//                        annotation_element159 = this.annotation_element();
//                        --this.state._fsp;
//                        stream_annotation_element.add(annotation_element159.getTree());
//                        break;
//                    default:
//                        END_ANNOTATION_DIRECTIVE160 = (Token)this.match(this.input, 24, FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2827);
//                        stream_END_ANNOTATION_DIRECTIVE.add(END_ANNOTATION_DIRECTIVE160);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(93, retval.start, "I_ANNOTATION"), root_1);
//                        this.adaptor.addChild(root_1, stream_ANNOTATION_VISIBILITY.nextNode());
//                        CommonTree root_2 = (CommonTree)this.adaptor.nil();
//                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(172, retval.start, "I_SUBANNOTATION"), root_2);
//                        this.adaptor.addChild(root_2, stream_CLASS_DESCRIPTOR.nextNode());
//
//                        while(stream_annotation_element.hasNext()) {
//                            this.adaptor.addChild(root_2, stream_annotation_element.nextTree());
//                        }
//
//                        stream_annotation_element.reset();
//                        this.adaptor.addChild(root_1, root_2);
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var23) {
//            this.reportError(var23);
//            this.recover(this.input, var23);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.subannotation_return subannotation() throws RecognitionException {
//        smaliParser.subannotation_return retval = new smaliParser.subannotation_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token SUBANNOTATION_DIRECTIVE161 = null;
//        Token CLASS_DESCRIPTOR162 = null;
//        Token END_SUBANNOTATION_DIRECTIVE164 = null;
//        ParserRuleReturnScope annotation_element163 = null;
//        CommonTree SUBANNOTATION_DIRECTIVE161_tree = null;
//        CommonTree CLASS_DESCRIPTOR162_tree = null;
//        CommonTree END_SUBANNOTATION_DIRECTIVE164_tree = null;
//        RewriteRuleTokenStream stream_SUBANNOTATION_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token SUBANNOTATION_DIRECTIVE");
//        RewriteRuleTokenStream stream_CLASS_DESCRIPTOR = new RewriteRuleTokenStream(this.adaptor, "token CLASS_DESCRIPTOR");
//        RewriteRuleTokenStream stream_END_SUBANNOTATION_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_SUBANNOTATION_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_annotation_element = new RewriteRuleSubtreeStream(this.adaptor, "rule annotation_element");
//
//        try {
//            SUBANNOTATION_DIRECTIVE161 = (Token)this.match(this.input, 199, FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation2860);
//            stream_SUBANNOTATION_DIRECTIVE.add(SUBANNOTATION_DIRECTIVE161);
//            CLASS_DESCRIPTOR162 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_subannotation2862);
//            stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR162);
//
//            while(true) {
//                int alt28 = 2;
//                int LA28_0 = this.input.LA(1);
//                if (LA28_0 == 4 || LA28_0 == 6 || LA28_0 == 10 || LA28_0 == 23 || LA28_0 == 39 || LA28_0 >= 42 && LA28_0 <= 44 || LA28_0 == 46 || LA28_0 == 48 || LA28_0 >= 51 && LA28_0 <= 56 || LA28_0 == 60 || LA28_0 >= 62 && LA28_0 <= 66 || LA28_0 >= 68 && LA28_0 <= 69 || LA28_0 >= 71 && LA28_0 <= 72 || LA28_0 >= 76 && LA28_0 <= 77 || LA28_0 >= 79 && LA28_0 <= 83 || LA28_0 == 89 || LA28_0 >= 181 && LA28_0 <= 182 || LA28_0 >= 187 && LA28_0 <= 189 || LA28_0 == 191 || LA28_0 == 195 || LA28_0 >= 201 && LA28_0 <= 202) {
//                    alt28 = 1;
//                }
//
//                switch(alt28) {
//                    case 1:
//                        this.pushFollow(FOLLOW_annotation_element_in_subannotation2864);
//                        annotation_element163 = this.annotation_element();
//                        --this.state._fsp;
//                        stream_annotation_element.add(annotation_element163.getTree());
//                        break;
//                    default:
//                        END_SUBANNOTATION_DIRECTIVE164 = (Token)this.match(this.input, 32, FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation2867);
//                        stream_END_SUBANNOTATION_DIRECTIVE.add(END_SUBANNOTATION_DIRECTIVE164);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(172, retval.start, "I_SUBANNOTATION"), root_1);
//                        this.adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
//
//                        while(stream_annotation_element.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_annotation_element.nextTree());
//                        }
//
//                        stream_annotation_element.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var19) {
//            this.reportError(var19);
//            this.recover(this.input, var19);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.enum_literal_return enum_literal() throws RecognitionException {
//        smaliParser.enum_literal_return retval = new smaliParser.enum_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ENUM_DIRECTIVE165 = null;
//        ParserRuleReturnScope field_reference166 = null;
//        CommonTree ENUM_DIRECTIVE165_tree = null;
//        RewriteRuleTokenStream stream_ENUM_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token ENUM_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_field_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule field_reference");
//
//        try {
//            try {
//                ENUM_DIRECTIVE165 = (Token)this.match(this.input, 33, FOLLOW_ENUM_DIRECTIVE_in_enum_literal2894);
//                stream_ENUM_DIRECTIVE.add(ENUM_DIRECTIVE165);
//                this.pushFollow(FOLLOW_field_reference_in_enum_literal2896);
//                field_reference166 = this.field_reference();
//                --this.state._fsp;
//                stream_field_reference.add(field_reference166.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(103, "I_ENCODED_ENUM"), root_1);
//                this.adaptor.addChild(root_1, stream_field_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.type_field_method_literal_return type_field_method_literal() throws RecognitionException {
//        smaliParser.type_field_method_literal_return retval = new smaliParser.type_field_method_literal_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ARROW169 = null;
//        Token COLON171 = null;
//        Token PRIMITIVE_TYPE175 = null;
//        Token VOID_TYPE176 = null;
//        ParserRuleReturnScope reference_type_descriptor167 = null;
//        ParserRuleReturnScope reference_type_descriptor168 = null;
//        ParserRuleReturnScope member_name170 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor172 = null;
//        ParserRuleReturnScope member_name173 = null;
//        ParserRuleReturnScope method_prototype174 = null;
//        CommonTree ARROW169_tree = null;
//        CommonTree COLON171_tree = null;
//        CommonTree PRIMITIVE_TYPE175_tree = null;
//        CommonTree VOID_TYPE176_tree = null;
//        RewriteRuleTokenStream stream_ARROW = new RewriteRuleTokenStream(this.adaptor, "token ARROW");
//        RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
//        RewriteRuleSubtreeStream stream_method_prototype = new RewriteRuleSubtreeStream(this.adaptor, "rule method_prototype");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//        RewriteRuleSubtreeStream stream_member_name = new RewriteRuleSubtreeStream(this.adaptor, "rule member_name");
//        RewriteRuleSubtreeStream stream_reference_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule reference_type_descriptor");
//
//        try {
//            try {
//                byte alt31;
//                int nvaeMark;
//                int LA31_5;
//                NoViableAltException nvae;
//                switch(this.input.LA(1)) {
//                    case 4:
//                    case 6:
//                    case 10:
//                    case 23:
//                    case 39:
//                    case 42:
//                    case 43:
//                    case 44:
//                    case 46:
//                    case 48:
//                    case 51:
//                    case 52:
//                    case 53:
//                    case 54:
//                    case 55:
//                    case 56:
//                    case 60:
//                    case 62:
//                    case 63:
//                    case 64:
//                    case 65:
//                    case 66:
//                    case 68:
//                    case 69:
//                    case 71:
//                    case 72:
//                    case 76:
//                    case 77:
//                    case 79:
//                    case 80:
//                    case 81:
//                    case 82:
//                    case 83:
//                    case 89:
//                    case 179:
//                    case 181:
//                    case 182:
//                    case 187:
//                    case 188:
//                    case 191:
//                    case 195:
//                    case 201:
//                        alt31 = 2;
//                        break;
//                    case 5:
//                    case 7:
//                    case 9:
//                    case 11:
//                    case 12:
//                    case 13:
//                    case 14:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 19:
//                    case 20:
//                    case 21:
//                    case 22:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 27:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 34:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 40:
//                    case 41:
//                    case 45:
//                    case 47:
//                    case 49:
//                    case 50:
//                    case 57:
//                    case 58:
//                    case 59:
//                    case 61:
//                    case 67:
//                    case 70:
//                    case 73:
//                    case 74:
//                    case 75:
//                    case 78:
//                    case 84:
//                    case 85:
//                    case 86:
//                    case 87:
//                    case 88:
//                    case 90:
//                    case 91:
//                    case 92:
//                    case 93:
//                    case 94:
//                    case 95:
//                    case 96:
//                    case 97:
//                    case 98:
//                    case 99:
//                    case 100:
//                    case 101:
//                    case 102:
//                    case 103:
//                    case 104:
//                    case 105:
//                    case 106:
//                    case 107:
//                    case 108:
//                    case 109:
//                    case 110:
//                    case 111:
//                    case 112:
//                    case 113:
//                    case 114:
//                    case 115:
//                    case 116:
//                    case 117:
//                    case 118:
//                    case 119:
//                    case 120:
//                    case 121:
//                    case 122:
//                    case 123:
//                    case 124:
//                    case 125:
//                    case 126:
//                    case 127:
//                    case 128:
//                    case 129:
//                    case 130:
//                    case 131:
//                    case 132:
//                    case 133:
//                    case 134:
//                    case 135:
//                    case 136:
//                    case 137:
//                    case 138:
//                    case 139:
//                    case 140:
//                    case 141:
//                    case 142:
//                    case 143:
//                    case 144:
//                    case 145:
//                    case 146:
//                    case 147:
//                    case 148:
//                    case 149:
//                    case 150:
//                    case 151:
//                    case 152:
//                    case 153:
//                    case 154:
//                    case 155:
//                    case 156:
//                    case 157:
//                    case 158:
//                    case 159:
//                    case 160:
//                    case 161:
//                    case 162:
//                    case 163:
//                    case 164:
//                    case 165:
//                    case 166:
//                    case 167:
//                    case 168:
//                    case 169:
//                    case 170:
//                    case 171:
//                    case 172:
//                    case 173:
//                    case 174:
//                    case 175:
//                    case 176:
//                    case 177:
//                    case 178:
//                    case 180:
//                    case 183:
//                    case 184:
//                    case 185:
//                    case 186:
//                    case 190:
//                    case 192:
//                    case 193:
//                    case 194:
//                    case 196:
//                    case 197:
//                    case 198:
//                    case 199:
//                    case 200:
//                    default:
//                        nvae = new NoViableAltException("", 31, 0, this.input);
//                        throw nvae;
//                    case 8:
//                        LA31_5 = this.input.LA(2);
//                        if (LA31_5 != 15 && LA31_5 != 189) {
//                            nvaeMark = this.input.mark();
//
//                            try {
//                                this.input.consume();
//                                nvae = new NoViableAltException("", 31, 2, this.input);
//                                throw nvae;
//                            } finally {
//                                this.input.rewind(nvaeMark);
//                            }
//                        }
//
//                        nvaeMark = this.input.LA(3);
//                        if (nvaeMark == -1 || nvaeMark >= 4 && nvaeMark <= 6 || nvaeMark == 10 || nvaeMark >= 16 && nvaeMark <= 17 || nvaeMark == 20 || nvaeMark >= 23 && nvaeMark <= 24 || nvaeMark == 26 || nvaeMark == 32 || nvaeMark == 36 || nvaeMark >= 39 && nvaeMark <= 40 || nvaeMark >= 42 && nvaeMark <= 44 || nvaeMark == 46 || nvaeMark == 48 || nvaeMark >= 51 && nvaeMark <= 56 || nvaeMark == 60 || nvaeMark >= 62 && nvaeMark <= 66 || nvaeMark >= 68 && nvaeMark <= 69 || nvaeMark >= 71 && nvaeMark <= 72 || nvaeMark >= 76 && nvaeMark <= 77 || nvaeMark >= 79 && nvaeMark <= 83 || nvaeMark == 89 || nvaeMark >= 180 && nvaeMark <= 182 || nvaeMark >= 187 && nvaeMark <= 189 || nvaeMark == 191 || nvaeMark >= 195 && nvaeMark <= 196 || nvaeMark >= 200 && nvaeMark <= 202) {
//                            alt31 = 1;
//                            break;
//                        }
//
//                        if (nvaeMark != 9) {
//                            nvaeMark = this.input.mark();
//
//                            try {
//                                for(int nvaeConsume = 0; nvaeConsume < 2; ++nvaeConsume) {
//                                    this.input.consume();
//                                }
//
//                                nvae = new NoViableAltException("", 31, 7, this.input);
//                                throw nvae;
//                            } finally {
//                                this.input.rewind(nvaeMark);
//                            }
//                        }
//
//                        alt31 = 2;
//                        break;
//                    case 15:
//                        LA31_5 = this.input.LA(2);
//                        if (LA31_5 == -1 || LA31_5 >= 4 && LA31_5 <= 6 || LA31_5 == 10 || LA31_5 >= 16 && LA31_5 <= 17 || LA31_5 == 20 || LA31_5 >= 23 && LA31_5 <= 24 || LA31_5 == 26 || LA31_5 == 32 || LA31_5 == 36 || LA31_5 >= 39 && LA31_5 <= 40 || LA31_5 >= 42 && LA31_5 <= 44 || LA31_5 == 46 || LA31_5 == 48 || LA31_5 >= 51 && LA31_5 <= 56 || LA31_5 == 60 || LA31_5 >= 62 && LA31_5 <= 66 || LA31_5 >= 68 && LA31_5 <= 69 || LA31_5 >= 71 && LA31_5 <= 72 || LA31_5 >= 76 && LA31_5 <= 77 || LA31_5 >= 79 && LA31_5 <= 83 || LA31_5 == 89 || LA31_5 >= 180 && LA31_5 <= 182 || LA31_5 >= 187 && LA31_5 <= 189 || LA31_5 == 191 || LA31_5 >= 195 && LA31_5 <= 196 || LA31_5 >= 200 && LA31_5 <= 202) {
//                            alt31 = 1;
//                            break;
//                        }
//
//                        if (LA31_5 != 9) {
//                            nvaeMark = this.input.mark();
//
//                            try {
//                                this.input.consume();
//                                nvae = new NoViableAltException("", 31, 1, this.input);
//                                throw nvae;
//                            } finally {
//                                this.input.rewind(nvaeMark);
//                            }
//                        }
//
//                        alt31 = 2;
//                        break;
//                    case 189:
//                        LA31_5 = this.input.LA(2);
//                        if (LA31_5 != 19 && LA31_5 != 184) {
//                            if (LA31_5 != -1 && (LA31_5 < 4 || LA31_5 > 6) && LA31_5 != 10 && (LA31_5 < 16 || LA31_5 > 17) && LA31_5 != 20 && (LA31_5 < 23 || LA31_5 > 24) && LA31_5 != 26 && LA31_5 != 32 && LA31_5 != 36 && (LA31_5 < 39 || LA31_5 > 40) && (LA31_5 < 42 || LA31_5 > 44) && LA31_5 != 46 && LA31_5 != 48 && (LA31_5 < 51 || LA31_5 > 56) && LA31_5 != 60 && (LA31_5 < 62 || LA31_5 > 66) && (LA31_5 < 68 || LA31_5 > 69) && (LA31_5 < 71 || LA31_5 > 72) && (LA31_5 < 76 || LA31_5 > 77) && (LA31_5 < 79 || LA31_5 > 83) && LA31_5 != 89 && (LA31_5 < 180 || LA31_5 > 182) && (LA31_5 < 187 || LA31_5 > 189) && LA31_5 != 191 && (LA31_5 < 195 || LA31_5 > 196) && (LA31_5 < 200 || LA31_5 > 202)) {
//                                nvaeMark = this.input.mark();
//
//                                try {
//                                    this.input.consume();
//                                    nvae = new NoViableAltException("", 31, 4, this.input);
//                                    throw nvae;
//                                } finally {
//                                    this.input.rewind(nvaeMark);
//                                }
//                            }
//
//                            alt31 = 3;
//                            break;
//                        }
//
//                        alt31 = 2;
//                        break;
//                    case 202:
//                        LA31_5 = this.input.LA(2);
//                        if (LA31_5 != 19 && LA31_5 != 184) {
//                            if (LA31_5 != -1 && (LA31_5 < 4 || LA31_5 > 6) && LA31_5 != 10 && (LA31_5 < 16 || LA31_5 > 17) && LA31_5 != 20 && (LA31_5 < 23 || LA31_5 > 24) && LA31_5 != 26 && LA31_5 != 32 && LA31_5 != 36 && (LA31_5 < 39 || LA31_5 > 40) && (LA31_5 < 42 || LA31_5 > 44) && LA31_5 != 46 && LA31_5 != 48 && (LA31_5 < 51 || LA31_5 > 56) && LA31_5 != 60 && (LA31_5 < 62 || LA31_5 > 66) && (LA31_5 < 68 || LA31_5 > 69) && (LA31_5 < 71 || LA31_5 > 72) && (LA31_5 < 76 || LA31_5 > 77) && (LA31_5 < 79 || LA31_5 > 83) && LA31_5 != 89 && (LA31_5 < 180 || LA31_5 > 182) && (LA31_5 < 187 || LA31_5 > 189) && LA31_5 != 191 && (LA31_5 < 195 || LA31_5 > 196) && (LA31_5 < 200 || LA31_5 > 202)) {
//                                nvaeMark = this.input.mark();
//
//                                try {
//                                    this.input.consume();
//                                    nvae = new NoViableAltException("", 31, 5, this.input);
//                                    throw nvae;
//                                } finally {
//                                    this.input.rewind(nvaeMark);
//                                }
//                            }
//
//                            alt31 = 4;
//                        } else {
//                            alt31 = 2;
//                        }
//                }
//
//                label3938:
//                switch(alt31) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_reference_type_descriptor_in_type_field_method_literal2916);
//                        reference_type_descriptor167 = this.reference_type_descriptor();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, reference_type_descriptor167.getTree());
//                        break;
//                    case 2:
//                        int alt29 = 2;
//                        nvaeMark = this.input.LA(1);
//                        if (nvaeMark == 8 || nvaeMark == 15) {
//                            alt29 = 1;
//                        }
//
//                        switch(alt29) {
//                            case 1:
//                                this.pushFollow(FOLLOW_reference_type_descriptor_in_type_field_method_literal2925);
//                                reference_type_descriptor168 = this.reference_type_descriptor();
//                                --this.state._fsp;
//                                stream_reference_type_descriptor.add(reference_type_descriptor168.getTree());
//                                ARROW169 = (Token)this.match(this.input, 9, FOLLOW_ARROW_in_type_field_method_literal2927);
//                                stream_ARROW.add(ARROW169);
//                            default:
//                                nvaeMark = this.dfa30.predict(this.input);
//                                CommonTree root_1;
//                                switch(nvaeMark) {
//                                    case 1:
//                                        this.pushFollow(FOLLOW_member_name_in_type_field_method_literal2939);
//                                        member_name170 = this.member_name();
//                                        --this.state._fsp;
//                                        stream_member_name.add(member_name170.getTree());
//                                        COLON171 = (Token)this.match(this.input, 19, FOLLOW_COLON_in_type_field_method_literal2941);
//                                        stream_COLON.add(COLON171);
//                                        this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal2943);
//                                        nonvoid_type_descriptor172 = this.nonvoid_type_descriptor();
//                                        --this.state._fsp;
//                                        stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor172.getTree());
//                                        retval.tree = root_0;
//                                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                        root_0 = (CommonTree)this.adaptor.nil();
//                                        root_1 = (CommonTree)this.adaptor.nil();
//                                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(104, "I_ENCODED_FIELD"), root_1);
//                                        if (stream_reference_type_descriptor.hasNext()) {
//                                            this.adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
//                                        }
//
//                                        stream_reference_type_descriptor.reset();
//                                        this.adaptor.addChild(root_1, stream_member_name.nextTree());
//                                        this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                                        this.adaptor.addChild(root_0, root_1);
//                                        retval.tree = root_0;
//                                        break label3938;
//                                    case 2:
//                                        this.pushFollow(FOLLOW_member_name_in_type_field_method_literal2966);
//                                        member_name173 = this.member_name();
//                                        --this.state._fsp;
//                                        stream_member_name.add(member_name173.getTree());
//                                        this.pushFollow(FOLLOW_method_prototype_in_type_field_method_literal2968);
//                                        method_prototype174 = this.method_prototype();
//                                        --this.state._fsp;
//                                        stream_method_prototype.add(method_prototype174.getTree());
//                                        retval.tree = root_0;
//                                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                        root_0 = (CommonTree)this.adaptor.nil();
//                                        root_1 = (CommonTree)this.adaptor.nil();
//                                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(105, "I_ENCODED_METHOD"), root_1);
//                                        if (stream_reference_type_descriptor.hasNext()) {
//                                            this.adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
//                                        }
//
//                                        stream_reference_type_descriptor.reset();
//                                        this.adaptor.addChild(root_1, stream_member_name.nextTree());
//                                        this.adaptor.addChild(root_1, stream_method_prototype.nextTree());
//                                        this.adaptor.addChild(root_0, root_1);
//                                        retval.tree = root_0;
//                                        break label3938;
//                                }
//                        }
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        PRIMITIVE_TYPE175 = (Token)this.match(this.input, 189, FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3001);
//                        PRIMITIVE_TYPE175_tree = (CommonTree)this.adaptor.create(PRIMITIVE_TYPE175);
//                        this.adaptor.addChild(root_0, PRIMITIVE_TYPE175_tree);
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        VOID_TYPE176 = (Token)this.match(this.input, 202, FOLLOW_VOID_TYPE_in_type_field_method_literal3007);
//                        VOID_TYPE176_tree = (CommonTree)this.adaptor.create(VOID_TYPE176);
//                        this.adaptor.addChild(root_0, VOID_TYPE176_tree);
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var80) {
//                this.reportError(var80);
//                this.recover(this.input, var80);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var80);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.method_reference_return method_reference() throws RecognitionException {
//        smaliParser.method_reference_return retval = new smaliParser.method_reference_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ARROW178 = null;
//        ParserRuleReturnScope reference_type_descriptor177 = null;
//        ParserRuleReturnScope member_name179 = null;
//        ParserRuleReturnScope method_prototype180 = null;
//        CommonTree ARROW178_tree = null;
//        RewriteRuleTokenStream stream_ARROW = new RewriteRuleTokenStream(this.adaptor, "token ARROW");
//        RewriteRuleSubtreeStream stream_method_prototype = new RewriteRuleSubtreeStream(this.adaptor, "rule method_prototype");
//        RewriteRuleSubtreeStream stream_member_name = new RewriteRuleSubtreeStream(this.adaptor, "rule member_name");
//        RewriteRuleSubtreeStream stream_reference_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule reference_type_descriptor");
//
//        try {
//            try {
//                int alt32 = 2;
//                int LA32_0 = this.input.LA(1);
//                if (LA32_0 == 8 || LA32_0 == 15) {
//                    alt32 = 1;
//                }
//
//                switch(alt32) {
//                    case 1:
//                        this.pushFollow(FOLLOW_reference_type_descriptor_in_method_reference3018);
//                        reference_type_descriptor177 = this.reference_type_descriptor();
//                        --this.state._fsp;
//                        stream_reference_type_descriptor.add(reference_type_descriptor177.getTree());
//                        ARROW178 = (Token)this.match(this.input, 9, FOLLOW_ARROW_in_method_reference3020);
//                        stream_ARROW.add(ARROW178);
//                    default:
//                        this.pushFollow(FOLLOW_member_name_in_method_reference3024);
//                        member_name179 = this.member_name();
//                        --this.state._fsp;
//                        stream_member_name.add(member_name179.getTree());
//                        this.pushFollow(FOLLOW_method_prototype_in_method_reference3026);
//                        method_prototype180 = this.method_prototype();
//                        --this.state._fsp;
//                        stream_method_prototype.add(method_prototype180.getTree());
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        if (stream_reference_type_descriptor.hasNext()) {
//                            this.adaptor.addChild(root_0, stream_reference_type_descriptor.nextTree());
//                        }
//
//                        stream_reference_type_descriptor.reset();
//                        this.adaptor.addChild(root_0, stream_member_name.nextTree());
//                        this.adaptor.addChild(root_0, stream_method_prototype.nextTree());
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                }
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.field_reference_return field_reference() throws RecognitionException {
//        smaliParser.field_reference_return retval = new smaliParser.field_reference_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ARROW182 = null;
//        Token COLON184 = null;
//        ParserRuleReturnScope reference_type_descriptor181 = null;
//        ParserRuleReturnScope member_name183 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor185 = null;
//        CommonTree ARROW182_tree = null;
//        CommonTree COLON184_tree = null;
//        RewriteRuleTokenStream stream_ARROW = new RewriteRuleTokenStream(this.adaptor, "token ARROW");
//        RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//        RewriteRuleSubtreeStream stream_member_name = new RewriteRuleSubtreeStream(this.adaptor, "rule member_name");
//        RewriteRuleSubtreeStream stream_reference_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule reference_type_descriptor");
//
//        try {
//            try {
//                int alt33 = 2;
//                int LA33_0 = this.input.LA(1);
//                if (LA33_0 == 8 || LA33_0 == 15) {
//                    alt33 = 1;
//                }
//
//                switch(alt33) {
//                    case 1:
//                        this.pushFollow(FOLLOW_reference_type_descriptor_in_field_reference3048);
//                        reference_type_descriptor181 = this.reference_type_descriptor();
//                        --this.state._fsp;
//                        stream_reference_type_descriptor.add(reference_type_descriptor181.getTree());
//                        ARROW182 = (Token)this.match(this.input, 9, FOLLOW_ARROW_in_field_reference3050);
//                        stream_ARROW.add(ARROW182);
//                    default:
//                        this.pushFollow(FOLLOW_member_name_in_field_reference3054);
//                        member_name183 = this.member_name();
//                        --this.state._fsp;
//                        stream_member_name.add(member_name183.getTree());
//                        COLON184 = (Token)this.match(this.input, 19, FOLLOW_COLON_in_field_reference3056);
//                        stream_COLON.add(COLON184);
//                        this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_field_reference3058);
//                        nonvoid_type_descriptor185 = this.nonvoid_type_descriptor();
//                        --this.state._fsp;
//                        stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor185.getTree());
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        if (stream_reference_type_descriptor.hasNext()) {
//                            this.adaptor.addChild(root_0, stream_reference_type_descriptor.nextTree());
//                        }
//
//                        stream_reference_type_descriptor.reset();
//                        this.adaptor.addChild(root_0, stream_member_name.nextTree());
//                        this.adaptor.addChild(root_0, stream_nonvoid_type_descriptor.nextTree());
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                }
//            } catch (RecognitionException var21) {
//                this.reportError(var21);
//                this.recover(this.input, var21);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var21);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.label_return label() throws RecognitionException {
//        smaliParser.label_return retval = new smaliParser.label_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token COLON186 = null;
//        ParserRuleReturnScope simple_name187 = null;
//        CommonTree COLON186_tree = null;
//        RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
//        RewriteRuleSubtreeStream stream_simple_name = new RewriteRuleSubtreeStream(this.adaptor, "rule simple_name");
//
//        try {
//            try {
//                COLON186 = (Token)this.match(this.input, 19, FOLLOW_COLON_in_label3079);
//                stream_COLON.add(COLON186);
//                this.pushFollow(FOLLOW_simple_name_in_label3081);
//                simple_name187 = this.simple_name();
//                --this.state._fsp;
//                stream_simple_name.add(simple_name187.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(113, COLON186, "I_LABEL"), root_1);
//                this.adaptor.addChild(root_1, stream_simple_name.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.label_ref_return label_ref() throws RecognitionException {
//        smaliParser.label_ref_return retval = new smaliParser.label_ref_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token COLON188 = null;
//        ParserRuleReturnScope simple_name189 = null;
//        CommonTree COLON188_tree = null;
//        RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
//        RewriteRuleSubtreeStream stream_simple_name = new RewriteRuleSubtreeStream(this.adaptor, "rule simple_name");
//
//        try {
//            try {
//                COLON188 = (Token)this.match(this.input, 19, FOLLOW_COLON_in_label_ref3100);
//                stream_COLON.add(COLON188);
//                this.pushFollow(FOLLOW_simple_name_in_label_ref3102);
//                simple_name189 = this.simple_name();
//                --this.state._fsp;
//                stream_simple_name.add(simple_name189.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                this.adaptor.addChild(root_0, stream_simple_name.nextTree());
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var12) {
//                this.reportError(var12);
//                this.recover(this.input, var12);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var12);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.register_list_return register_list() throws RecognitionException {
//        smaliParser.register_list_return retval = new smaliParser.register_list_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token REGISTER190 = null;
//        Token COMMA191 = null;
//        Token REGISTER192 = null;
//        CommonTree REGISTER190_tree = null;
//        CommonTree COMMA191_tree = null;
//        CommonTree REGISTER192_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//
//        try {
//            try {
//                int LA35_0 = this.input.LA(1);
//                byte alt35;
//                if (LA35_0 == 191) {
//                    alt35 = 1;
//                } else {
//                    if (LA35_0 != 17) {
//                        NoViableAltException nvae = new NoViableAltException("", 35, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt35 = 2;
//                }
//
//                CommonTree root_1;
//                label109:
//                switch(alt35) {
//                    case 1:
//                        REGISTER190 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_register_list3116);
//                        stream_REGISTER.add(REGISTER190);
//
//                        while(true) {
//                            int alt34 = 2;
//                            int LA34_0 = this.input.LA(1);
//                            if (LA34_0 == 20) {
//                                alt34 = 1;
//                            }
//
//                            switch(alt34) {
//                                case 1:
//                                    COMMA191 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_register_list3119);
//                                    stream_COMMA.add(COMMA191);
//                                    REGISTER192 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_register_list3121);
//                                    stream_REGISTER.add(REGISTER192);
//                                    break;
//                                default:
//                                    retval.tree = root_0;
//                                    new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                    root_0 = (CommonTree)this.adaptor.nil();
//                                    root_1 = (CommonTree)this.adaptor.nil();
//                                    root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(129, retval.start, "I_REGISTER_LIST"), root_1);
//
//                                    while(stream_REGISTER.hasNext()) {
//                                        this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                                    }
//
//                                    stream_REGISTER.reset();
//                                    this.adaptor.addChild(root_0, root_1);
//                                    retval.tree = root_0;
//                                    break label109;
//                            }
//                        }
//                    case 2:
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(129, retval.start, "I_REGISTER_LIST"), root_1);
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.register_range_return register_range() throws RecognitionException {
//        smaliParser.register_range_return retval = new smaliParser.register_range_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token startreg = null;
//        Token endreg = null;
//        Token DOTDOT193 = null;
//        CommonTree startreg_tree = null;
//        CommonTree endreg_tree = null;
//        CommonTree DOTDOT193_tree = null;
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_DOTDOT = new RewriteRuleTokenStream(this.adaptor, "token DOTDOT");
//
//        try {
//            try {
//                int alt37 = 2;
//                int LA37_0 = this.input.LA(1);
//                if (LA37_0 == 191) {
//                    alt37 = 1;
//                }
//
//                switch(alt37) {
//                    case 1:
//                        startreg = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_register_range3156);
//                        stream_REGISTER.add(startreg);
//                        int alt36 = 2;
//                        int LA36_0 = this.input.LA(1);
//                        if (LA36_0 == 21) {
//                            alt36 = 1;
//                        }
//
//                        switch(alt36) {
//                            case 1:
//                                DOTDOT193 = (Token)this.match(this.input, 21, FOLLOW_DOTDOT_in_register_range3159);
//                                stream_DOTDOT.add(DOTDOT193);
//                                endreg = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_register_range3163);
//                                stream_REGISTER.add(endreg);
//                        }
//                    default:
//                        retval.tree = root_0;
//                        RewriteRuleTokenStream stream_endreg = new RewriteRuleTokenStream(this.adaptor, "token endreg", endreg);
//                        RewriteRuleTokenStream stream_startreg = new RewriteRuleTokenStream(this.adaptor, "token startreg", startreg);
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(130, retval.start, "I_REGISTER_RANGE"), root_1);
//                        if (stream_startreg.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_startreg.nextNode());
//                        }
//
//                        stream_startreg.reset();
//                        if (stream_endreg.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_endreg.nextNode());
//                        }
//
//                        stream_endreg.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                }
//            } catch (RecognitionException var20) {
//                this.reportError(var20);
//                this.recover(this.input, var20);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.verification_error_reference_return verification_error_reference() throws RecognitionException {
//        smaliParser.verification_error_reference_return retval = new smaliParser.verification_error_reference_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token CLASS_DESCRIPTOR194 = null;
//        ParserRuleReturnScope field_reference195 = null;
//        ParserRuleReturnScope method_reference196 = null;
//        CommonTree CLASS_DESCRIPTOR194_tree = null;
//
//        try {
//            try {
//                int alt38 = this.dfa38.predict(this.input);
//                switch(alt38) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CLASS_DESCRIPTOR194 = (Token)this.match(this.input, 15, FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3192);
//                        CLASS_DESCRIPTOR194_tree = (CommonTree)this.adaptor.create(CLASS_DESCRIPTOR194);
//                        this.adaptor.addChild(root_0, CLASS_DESCRIPTOR194_tree);
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_field_reference_in_verification_error_reference3196);
//                        field_reference195 = this.field_reference();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, field_reference195.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_method_reference_in_verification_error_reference3200);
//                        method_reference196 = this.method_reference();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, method_reference196.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var11) {
//                this.reportError(var11);
//                this.recover(this.input, var11);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.catch_directive_return catch_directive() throws RecognitionException {
//        smaliParser.catch_directive_return retval = new smaliParser.catch_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token CATCH_DIRECTIVE197 = null;
//        Token OPEN_BRACE199 = null;
//        Token DOTDOT200 = null;
//        Token CLOSE_BRACE201 = null;
//        ParserRuleReturnScope from = null;
//        ParserRuleReturnScope to = null;
//        ParserRuleReturnScope using = null;
//        ParserRuleReturnScope nonvoid_type_descriptor198 = null;
//        CommonTree CATCH_DIRECTIVE197_tree = null;
//        CommonTree OPEN_BRACE199_tree = null;
//        CommonTree DOTDOT200_tree = null;
//        CommonTree CLOSE_BRACE201_tree = null;
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleTokenStream stream_DOTDOT = new RewriteRuleTokenStream(this.adaptor, "token DOTDOT");
//        RewriteRuleTokenStream stream_CATCH_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token CATCH_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//
//        try {
//            try {
//                CATCH_DIRECTIVE197 = (Token)this.match(this.input, 13, FOLLOW_CATCH_DIRECTIVE_in_catch_directive3210);
//                stream_CATCH_DIRECTIVE.add(CATCH_DIRECTIVE197);
//                this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_catch_directive3212);
//                nonvoid_type_descriptor198 = this.nonvoid_type_descriptor();
//                --this.state._fsp;
//                stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor198.getTree());
//                OPEN_BRACE199 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_catch_directive3214);
//                stream_OPEN_BRACE.add(OPEN_BRACE199);
//                this.pushFollow(FOLLOW_label_ref_in_catch_directive3218);
//                from = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(from.getTree());
//                DOTDOT200 = (Token)this.match(this.input, 21, FOLLOW_DOTDOT_in_catch_directive3220);
//                stream_DOTDOT.add(DOTDOT200);
//                this.pushFollow(FOLLOW_label_ref_in_catch_directive3224);
//                to = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(to.getTree());
//                CLOSE_BRACE201 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_catch_directive3226);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE201);
//                this.pushFollow(FOLLOW_label_ref_in_catch_directive3230);
//                using = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(using.getTree());
//                retval.tree = root_0;
//                RewriteRuleSubtreeStream stream_using = new RewriteRuleSubtreeStream(this.adaptor, "rule using", using != null ? using.getTree() : null);
//                RewriteRuleSubtreeStream stream_from = new RewriteRuleSubtreeStream(this.adaptor, "rule from", from != null ? from.getTree() : null);
//                RewriteRuleSubtreeStream stream_to = new RewriteRuleSubtreeStream(this.adaptor, "rule to", to != null ? to.getTree() : null);
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(98, retval.start, "I_CATCH"), root_1);
//                this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                this.adaptor.addChild(root_1, stream_from.nextTree());
//                this.adaptor.addChild(root_1, stream_to.nextTree());
//                this.adaptor.addChild(root_1, stream_using.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var29) {
//                this.reportError(var29);
//                this.recover(this.input, var29);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var29);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.catchall_directive_return catchall_directive() throws RecognitionException {
//        smaliParser.catchall_directive_return retval = new smaliParser.catchall_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token CATCHALL_DIRECTIVE202 = null;
//        Token OPEN_BRACE203 = null;
//        Token DOTDOT204 = null;
//        Token CLOSE_BRACE205 = null;
//        ParserRuleReturnScope from = null;
//        ParserRuleReturnScope to = null;
//        ParserRuleReturnScope using = null;
//        CommonTree CATCHALL_DIRECTIVE202_tree = null;
//        CommonTree OPEN_BRACE203_tree = null;
//        CommonTree DOTDOT204_tree = null;
//        CommonTree CLOSE_BRACE205_tree = null;
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleTokenStream stream_DOTDOT = new RewriteRuleTokenStream(this.adaptor, "token DOTDOT");
//        RewriteRuleTokenStream stream_CATCHALL_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token CATCHALL_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                CATCHALL_DIRECTIVE202 = (Token)this.match(this.input, 12, FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3262);
//                stream_CATCHALL_DIRECTIVE.add(CATCHALL_DIRECTIVE202);
//                OPEN_BRACE203 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_catchall_directive3264);
//                stream_OPEN_BRACE.add(OPEN_BRACE203);
//                this.pushFollow(FOLLOW_label_ref_in_catchall_directive3268);
//                from = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(from.getTree());
//                DOTDOT204 = (Token)this.match(this.input, 21, FOLLOW_DOTDOT_in_catchall_directive3270);
//                stream_DOTDOT.add(DOTDOT204);
//                this.pushFollow(FOLLOW_label_ref_in_catchall_directive3274);
//                to = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(to.getTree());
//                CLOSE_BRACE205 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_catchall_directive3276);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE205);
//                this.pushFollow(FOLLOW_label_ref_in_catchall_directive3280);
//                using = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(using.getTree());
//                retval.tree = root_0;
//                RewriteRuleSubtreeStream stream_using = new RewriteRuleSubtreeStream(this.adaptor, "rule using", using != null ? using.getTree() : null);
//                RewriteRuleSubtreeStream stream_from = new RewriteRuleSubtreeStream(this.adaptor, "rule from", from != null ? from.getTree() : null);
//                RewriteRuleSubtreeStream stream_to = new RewriteRuleSubtreeStream(this.adaptor, "rule to", to != null ? to.getTree() : null);
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(99, retval.start, "I_CATCHALL"), root_1);
//                this.adaptor.addChild(root_1, stream_from.nextTree());
//                this.adaptor.addChild(root_1, stream_to.nextTree());
//                this.adaptor.addChild(root_1, stream_using.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var27) {
//                this.reportError(var27);
//                this.recover(this.input, var27);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var27);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.parameter_directive_return parameter_directive() throws RecognitionException {
//        smaliParser.parameter_directive_return retval = new smaliParser.parameter_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token PARAMETER_DIRECTIVE206 = null;
//        Token REGISTER207 = null;
//        Token COMMA208 = null;
//        Token STRING_LITERAL209 = null;
//        Token END_PARAMETER_DIRECTIVE211 = null;
//        ParserRuleReturnScope annotation210 = null;
//        CommonTree PARAMETER_DIRECTIVE206_tree = null;
//        CommonTree REGISTER207_tree = null;
//        CommonTree COMMA208_tree = null;
//        CommonTree STRING_LITERAL209_tree = null;
//        CommonTree END_PARAMETER_DIRECTIVE211_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_PARAMETER_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token PARAMETER_DIRECTIVE");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//        RewriteRuleTokenStream stream_END_PARAMETER_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_PARAMETER_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_annotation = new RewriteRuleSubtreeStream(this.adaptor, "rule annotation");
//        ArrayList annotations = new ArrayList();
//
//        try {
//            PARAMETER_DIRECTIVE206 = (Token)this.match(this.input, 186, FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3319);
//            stream_PARAMETER_DIRECTIVE.add(PARAMETER_DIRECTIVE206);
//            REGISTER207 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_parameter_directive3321);
//            stream_REGISTER.add(REGISTER207);
//            int alt39 = 2;
//            int LA39_0 = this.input.LA(1);
//            if (LA39_0 == 20) {
//                alt39 = 1;
//            }
//
//            switch(alt39) {
//                case 1:
//                    COMMA208 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_parameter_directive3324);
//                    stream_COMMA.add(COMMA208);
//                    STRING_LITERAL209 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_parameter_directive3326);
//                    stream_STRING_LITERAL.add(STRING_LITERAL209);
//            }
//
//            while(true) {
//                int alt40 = this.dfa40.predict(this.input);
//                switch(alt40) {
//                    case 1:
//                        if (this.input.LA(1) != 5) {
//                            throw new FailedPredicateException(this.input, "parameter_directive", "input.LA(1) == ANNOTATION_DIRECTIVE");
//                        }
//
//                        this.pushFollow(FOLLOW_annotation_in_parameter_directive3337);
//                        annotation210 = this.annotation();
//                        --this.state._fsp;
//                        stream_annotation.add(annotation210.getTree());
//                        annotations.add(annotation210 != null ? (CommonTree)annotation210.getTree() : null);
//                        break;
//                    default:
//                        int LA41_0 = this.input.LA(1);
//                        byte alt41;
//                        if (LA41_0 == 30) {
//                            alt41 = 1;
//                        } else {
//                            if (LA41_0 != 5 && LA41_0 != 7 && (LA41_0 < 12 || LA41_0 > 13) && LA41_0 != 19 && (LA41_0 < 27 || LA41_0 > 28) && LA41_0 != 34 && (LA41_0 < 42 || LA41_0 > 89) && (LA41_0 < 175 || LA41_0 > 177) && (LA41_0 < 185 || LA41_0 > 186) && LA41_0 != 190 && (LA41_0 < 192 || LA41_0 > 193) && (LA41_0 < 196 || LA41_0 > 197)) {
//                                NoViableAltException nvae = new NoViableAltException("", 41, 0, this.input);
//                                throw nvae;
//                            }
//
//                            alt41 = 2;
//                        }
//
//                        CommonTree root_1;
//                        CommonTree root_2;
//                        switch(alt41) {
//                            case 1:
//                                END_PARAMETER_DIRECTIVE211 = (Token)this.match(this.input, 30, FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3350);
//                                stream_END_PARAMETER_DIRECTIVE.add(END_PARAMETER_DIRECTIVE211);
//                                retval.tree = root_0;
//                                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                root_0 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(124, retval.start, "I_PARAMETER"), root_1);
//                                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                                if (stream_STRING_LITERAL.hasNext()) {
//                                    this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                                }
//
//                                stream_STRING_LITERAL.reset();
//                                root_2 = (CommonTree)this.adaptor.nil();
//                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(94, "I_ANNOTATIONS"), root_2);
//
//                                while(stream_annotation.hasNext()) {
//                                    this.adaptor.addChild(root_2, stream_annotation.nextTree());
//                                }
//
//                                stream_annotation.reset();
//                                this.adaptor.addChild(root_1, root_2);
//                                this.adaptor.addChild(root_0, root_1);
//                                retval.tree = root_0;
//                                break;
//                            case 2:
//                                ((smaliParser.statements_and_directives_scope)this.statements_and_directives_stack.peek()).methodAnnotations.addAll(annotations);
//                                retval.tree = root_0;
//                                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                                root_0 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.nil();
//                                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(124, retval.start, "I_PARAMETER"), root_1);
//                                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                                if (stream_STRING_LITERAL.hasNext()) {
//                                    this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                                }
//
//                                stream_STRING_LITERAL.reset();
//                                root_2 = (CommonTree)this.adaptor.nil();
//                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(94, "I_ANNOTATIONS"), root_2);
//                                this.adaptor.addChild(root_1, root_2);
//                                this.adaptor.addChild(root_0, root_1);
//                                retval.tree = root_0;
//                        }
//
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var31) {
//            this.reportError(var31);
//            this.recover(this.input, var31);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var31);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.debug_directive_return debug_directive() throws RecognitionException {
//        smaliParser.debug_directive_return retval = new smaliParser.debug_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        ParserRuleReturnScope line_directive212 = null;
//        ParserRuleReturnScope local_directive213 = null;
//        ParserRuleReturnScope end_local_directive214 = null;
//        ParserRuleReturnScope restart_local_directive215 = null;
//        ParserRuleReturnScope prologue_directive216 = null;
//        ParserRuleReturnScope epilogue_directive217 = null;
//        smaliParser.source_directive_return source_directive218 = null;
//
//        try {
//            try {
//                byte alt42;
//                switch(this.input.LA(1)) {
//                    case 27:
//                        alt42 = 3;
//                        break;
//                    case 34:
//                        alt42 = 6;
//                        break;
//                    case 175:
//                        alt42 = 1;
//                        break;
//                    case 177:
//                        alt42 = 2;
//                        break;
//                    case 190:
//                        alt42 = 5;
//                        break;
//                    case 193:
//                        alt42 = 4;
//                        break;
//                    case 196:
//                        alt42 = 7;
//                        break;
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 42, 0, this.input);
//                        throw nvae;
//                }
//
//                switch(alt42) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_line_directive_in_debug_directive3423);
//                        line_directive212 = this.line_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, line_directive212.getTree());
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_local_directive_in_debug_directive3429);
//                        local_directive213 = this.local_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, local_directive213.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_end_local_directive_in_debug_directive3435);
//                        end_local_directive214 = this.end_local_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, end_local_directive214.getTree());
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_restart_local_directive_in_debug_directive3441);
//                        restart_local_directive215 = this.restart_local_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, restart_local_directive215.getTree());
//                        break;
//                    case 5:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_prologue_directive_in_debug_directive3447);
//                        prologue_directive216 = this.prologue_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, prologue_directive216.getTree());
//                        break;
//                    case 6:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_epilogue_directive_in_debug_directive3453);
//                        epilogue_directive217 = this.epilogue_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, epilogue_directive217.getTree());
//                        break;
//                    case 7:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_source_directive_in_debug_directive3459);
//                        source_directive218 = this.source_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, source_directive218.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var15) {
//                this.reportError(var15);
//                this.recover(this.input, var15);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var15);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.line_directive_return line_directive() throws RecognitionException {
//        smaliParser.line_directive_return retval = new smaliParser.line_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token LINE_DIRECTIVE219 = null;
//        ParserRuleReturnScope integral_literal220 = null;
//        CommonTree LINE_DIRECTIVE219_tree = null;
//        RewriteRuleTokenStream stream_LINE_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token LINE_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_integral_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule integral_literal");
//
//        try {
//            try {
//                LINE_DIRECTIVE219 = (Token)this.match(this.input, 175, FOLLOW_LINE_DIRECTIVE_in_line_directive3469);
//                stream_LINE_DIRECTIVE.add(LINE_DIRECTIVE219);
//                this.pushFollow(FOLLOW_integral_literal_in_line_directive3471);
//                integral_literal220 = this.integral_literal();
//                --this.state._fsp;
//                stream_integral_literal.add(integral_literal220.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(114, retval.start, "I_LINE"), root_1);
//                this.adaptor.addChild(root_1, stream_integral_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.local_directive_return local_directive() throws RecognitionException {
//        smaliParser.local_directive_return retval = new smaliParser.local_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token name = null;
//        Token signature = null;
//        Token LOCAL_DIRECTIVE221 = null;
//        Token REGISTER222 = null;
//        Token COMMA223 = null;
//        Token NULL_LITERAL224 = null;
//        Token COLON225 = null;
//        Token VOID_TYPE226 = null;
//        Token COMMA228 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor227 = null;
//        CommonTree name_tree = null;
//        CommonTree signature_tree = null;
//        CommonTree LOCAL_DIRECTIVE221_tree = null;
//        CommonTree REGISTER222_tree = null;
//        CommonTree COMMA223_tree = null;
//        CommonTree NULL_LITERAL224_tree = null;
//        CommonTree COLON225_tree = null;
//        CommonTree VOID_TYPE226_tree = null;
//        CommonTree COMMA228_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_LOCAL_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token LOCAL_DIRECTIVE");
//        RewriteRuleTokenStream stream_VOID_TYPE = new RewriteRuleTokenStream(this.adaptor, "token VOID_TYPE");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//        RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
//        RewriteRuleTokenStream stream_NULL_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token NULL_LITERAL");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//
//        try {
//            try {
//                LOCAL_DIRECTIVE221 = (Token)this.match(this.input, 177, FOLLOW_LOCAL_DIRECTIVE_in_local_directive3494);
//                stream_LOCAL_DIRECTIVE.add(LOCAL_DIRECTIVE221);
//                REGISTER222 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_local_directive3496);
//                stream_REGISTER.add(REGISTER222);
//                int alt46 = 2;
//                int LA46_0 = this.input.LA(1);
//                if (LA46_0 == 20) {
//                    alt46 = 1;
//                }
//
//                switch(alt46) {
//                    case 1:
//                        COMMA223 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_local_directive3499);
//                        stream_COMMA.add(COMMA223);
//                        int LA43_0 = this.input.LA(1);
//                        byte alt43;
//                        if (LA43_0 == 182) {
//                            alt43 = 1;
//                        } else {
//                            if (LA43_0 != 198) {
//                                NoViableAltException nvae = new NoViableAltException("", 43, 0, this.input);
//                                throw nvae;
//                            }
//
//                            alt43 = 2;
//                        }
//
//                        switch(alt43) {
//                            case 1:
//                                NULL_LITERAL224 = (Token)this.match(this.input, 182, FOLLOW_NULL_LITERAL_in_local_directive3502);
//                                stream_NULL_LITERAL.add(NULL_LITERAL224);
//                                break;
//                            case 2:
//                                name = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_local_directive3508);
//                                stream_STRING_LITERAL.add(name);
//                        }
//
//                        COLON225 = (Token)this.match(this.input, 19, FOLLOW_COLON_in_local_directive3511);
//                        stream_COLON.add(COLON225);
//                        int LA44_0 = this.input.LA(1);
//                        byte alt44;
//                        if (LA44_0 == 202) {
//                            alt44 = 1;
//                        } else {
//                            if (LA44_0 != 8 && LA44_0 != 15 && LA44_0 != 189) {
//                                NoViableAltException nvae = new NoViableAltException("", 44, 0, this.input);
//                                throw nvae;
//                            }
//
//                            alt44 = 2;
//                        }
//
//                        switch(alt44) {
//                            case 1:
//                                VOID_TYPE226 = (Token)this.match(this.input, 202, FOLLOW_VOID_TYPE_in_local_directive3514);
//                                stream_VOID_TYPE.add(VOID_TYPE226);
//                                break;
//                            case 2:
//                                this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_local_directive3518);
//                                nonvoid_type_descriptor227 = this.nonvoid_type_descriptor();
//                                --this.state._fsp;
//                                stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor227.getTree());
//                        }
//
//                        int alt45 = 2;
//                        int LA45_0 = this.input.LA(1);
//                        if (LA45_0 == 20) {
//                            alt45 = 1;
//                        }
//
//                        switch(alt45) {
//                            case 1:
//                                COMMA228 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_local_directive3552);
//                                stream_COMMA.add(COMMA228);
//                                signature = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_local_directive3556);
//                                stream_STRING_LITERAL.add(signature);
//                        }
//                    default:
//                        retval.tree = root_0;
//                        RewriteRuleTokenStream stream_signature = new RewriteRuleTokenStream(this.adaptor, "token signature", signature);
//                        RewriteRuleTokenStream stream_name = new RewriteRuleTokenStream(this.adaptor, "token name", name);
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(115, retval.start, "I_LOCAL"), root_1);
//                        this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                        if (stream_NULL_LITERAL.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_NULL_LITERAL.nextNode());
//                        }
//
//                        stream_NULL_LITERAL.reset();
//                        if (stream_name.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_name.nextNode());
//                        }
//
//                        stream_name.reset();
//                        if (stream_nonvoid_type_descriptor.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                        }
//
//                        stream_nonvoid_type_descriptor.reset();
//                        if (stream_signature.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_signature.nextNode());
//                        }
//
//                        stream_signature.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                }
//            } catch (RecognitionException var41) {
//                this.reportError(var41);
//                this.recover(this.input, var41);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var41);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.end_local_directive_return end_local_directive() throws RecognitionException {
//        smaliParser.end_local_directive_return retval = new smaliParser.end_local_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token END_LOCAL_DIRECTIVE229 = null;
//        Token REGISTER230 = null;
//        CommonTree END_LOCAL_DIRECTIVE229_tree = null;
//        CommonTree REGISTER230_tree = null;
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_END_LOCAL_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_LOCAL_DIRECTIVE");
//
//        try {
//            try {
//                END_LOCAL_DIRECTIVE229 = (Token)this.match(this.input, 27, FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3598);
//                stream_END_LOCAL_DIRECTIVE.add(END_LOCAL_DIRECTIVE229);
//                REGISTER230 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_end_local_directive3600);
//                stream_REGISTER.add(REGISTER230);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(106, retval.start, "I_END_LOCAL"), root_1);
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.restart_local_directive_return restart_local_directive() throws RecognitionException {
//        smaliParser.restart_local_directive_return retval = new smaliParser.restart_local_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token RESTART_LOCAL_DIRECTIVE231 = null;
//        Token REGISTER232 = null;
//        CommonTree RESTART_LOCAL_DIRECTIVE231_tree = null;
//        CommonTree REGISTER232_tree = null;
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_RESTART_LOCAL_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token RESTART_LOCAL_DIRECTIVE");
//
//        try {
//            try {
//                RESTART_LOCAL_DIRECTIVE231 = (Token)this.match(this.input, 193, FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3623);
//                stream_RESTART_LOCAL_DIRECTIVE.add(RESTART_LOCAL_DIRECTIVE231);
//                REGISTER232 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_restart_local_directive3625);
//                stream_REGISTER.add(REGISTER232);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(131, retval.start, "I_RESTART_LOCAL"), root_1);
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.prologue_directive_return prologue_directive() throws RecognitionException {
//        smaliParser.prologue_directive_return retval = new smaliParser.prologue_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token PROLOGUE_DIRECTIVE233 = null;
//        CommonTree PROLOGUE_DIRECTIVE233_tree = null;
//        RewriteRuleTokenStream stream_PROLOGUE_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token PROLOGUE_DIRECTIVE");
//
//        try {
//            try {
//                PROLOGUE_DIRECTIVE233 = (Token)this.match(this.input, 190, FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3648);
//                stream_PROLOGUE_DIRECTIVE.add(PROLOGUE_DIRECTIVE233);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(127, retval.start, "I_PROLOGUE"), root_1);
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var11) {
//                this.reportError(var11);
//                this.recover(this.input, var11);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.epilogue_directive_return epilogue_directive() throws RecognitionException {
//        smaliParser.epilogue_directive_return retval = new smaliParser.epilogue_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token EPILOGUE_DIRECTIVE234 = null;
//        CommonTree EPILOGUE_DIRECTIVE234_tree = null;
//        RewriteRuleTokenStream stream_EPILOGUE_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token EPILOGUE_DIRECTIVE");
//
//        try {
//            try {
//                EPILOGUE_DIRECTIVE234 = (Token)this.match(this.input, 34, FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3669);
//                stream_EPILOGUE_DIRECTIVE.add(EPILOGUE_DIRECTIVE234);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(107, retval.start, "I_EPILOGUE"), root_1);
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var11) {
//                this.reportError(var11);
//                this.recover(this.input, var11);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.source_directive_return source_directive() throws RecognitionException {
//        smaliParser.source_directive_return retval = new smaliParser.source_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token SOURCE_DIRECTIVE235 = null;
//        Token STRING_LITERAL236 = null;
//        CommonTree SOURCE_DIRECTIVE235_tree = null;
//        CommonTree STRING_LITERAL236_tree = null;
//        RewriteRuleTokenStream stream_SOURCE_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token SOURCE_DIRECTIVE");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//
//        try {
//            try {
//                SOURCE_DIRECTIVE235 = (Token)this.match(this.input, 196, FOLLOW_SOURCE_DIRECTIVE_in_source_directive3690);
//                stream_SOURCE_DIRECTIVE.add(SOURCE_DIRECTIVE235);
//                int alt47 = 2;
//                int LA47_0 = this.input.LA(1);
//                if (LA47_0 == 198) {
//                    alt47 = 1;
//                }
//
//                switch(alt47) {
//                    case 1:
//                        STRING_LITERAL236 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_source_directive3692);
//                        stream_STRING_LITERAL.add(STRING_LITERAL236);
//                    default:
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(132, retval.start, "I_SOURCE"), root_1);
//                        if (stream_STRING_LITERAL.hasNext()) {
//                            this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                        }
//
//                        stream_STRING_LITERAL.reset();
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                }
//            } catch (RecognitionException var16) {
//                this.reportError(var16);
//                this.recover(this.input, var16);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var16);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.instruction_format12x_return instruction_format12x() throws RecognitionException {
//        smaliParser.instruction_format12x_return retval = new smaliParser.instruction_format12x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT12x237 = null;
//        Token INSTRUCTION_FORMAT12x_OR_ID238 = null;
//        CommonTree INSTRUCTION_FORMAT12x237_tree = null;
//        CommonTree INSTRUCTION_FORMAT12x_OR_ID238_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT12x_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT12x_OR_ID");
//
//        try {
//            try {
//                int LA48_0 = this.input.LA(1);
//                byte alt48;
//                if (LA48_0 == 47) {
//                    alt48 = 1;
//                } else {
//                    if (LA48_0 != 48) {
//                        NoViableAltException nvae = new NoViableAltException("", 48, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt48 = 2;
//                }
//
//                switch(alt48) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        INSTRUCTION_FORMAT12x237 = (Token)this.match(this.input, 47, FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x3717);
//                        INSTRUCTION_FORMAT12x237_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT12x237);
//                        this.adaptor.addChild(root_0, INSTRUCTION_FORMAT12x237_tree);
//                        break;
//                    case 2:
//                        INSTRUCTION_FORMAT12x_OR_ID238 = (Token)this.match(this.input, 48, FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x3723);
//                        stream_INSTRUCTION_FORMAT12x_OR_ID.add(INSTRUCTION_FORMAT12x_OR_ID238);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(47, INSTRUCTION_FORMAT12x_OR_ID238));
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.instruction_format22s_return instruction_format22s() throws RecognitionException {
//        smaliParser.instruction_format22s_return retval = new smaliParser.instruction_format22s_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22s239 = null;
//        Token INSTRUCTION_FORMAT22s_OR_ID240 = null;
//        CommonTree INSTRUCTION_FORMAT22s239_tree = null;
//        CommonTree INSTRUCTION_FORMAT22s_OR_ID240_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22s_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22s_OR_ID");
//
//        try {
//            try {
//                int LA49_0 = this.input.LA(1);
//                byte alt49;
//                if (LA49_0 == 67) {
//                    alt49 = 1;
//                } else {
//                    if (LA49_0 != 68) {
//                        NoViableAltException nvae = new NoViableAltException("", 49, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt49 = 2;
//                }
//
//                switch(alt49) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        INSTRUCTION_FORMAT22s239 = (Token)this.match(this.input, 67, FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s3738);
//                        INSTRUCTION_FORMAT22s239_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT22s239);
//                        this.adaptor.addChild(root_0, INSTRUCTION_FORMAT22s239_tree);
//                        break;
//                    case 2:
//                        INSTRUCTION_FORMAT22s_OR_ID240 = (Token)this.match(this.input, 68, FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s3744);
//                        stream_INSTRUCTION_FORMAT22s_OR_ID.add(INSTRUCTION_FORMAT22s_OR_ID240);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(67, INSTRUCTION_FORMAT22s_OR_ID240));
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.instruction_format31i_return instruction_format31i() throws RecognitionException {
//        smaliParser.instruction_format31i_return retval = new smaliParser.instruction_format31i_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT31i241 = null;
//        Token INSTRUCTION_FORMAT31i_OR_ID242 = null;
//        CommonTree INSTRUCTION_FORMAT31i241_tree = null;
//        CommonTree INSTRUCTION_FORMAT31i_OR_ID242_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31i_OR_ID = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT31i_OR_ID");
//
//        try {
//            try {
//                int LA50_0 = this.input.LA(1);
//                byte alt50;
//                if (LA50_0 == 75) {
//                    alt50 = 1;
//                } else {
//                    if (LA50_0 != 76) {
//                        NoViableAltException nvae = new NoViableAltException("", 50, 0, this.input);
//                        throw nvae;
//                    }
//
//                    alt50 = 2;
//                }
//
//                switch(alt50) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        INSTRUCTION_FORMAT31i241 = (Token)this.match(this.input, 75, FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i3759);
//                        INSTRUCTION_FORMAT31i241_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT31i241);
//                        this.adaptor.addChild(root_0, INSTRUCTION_FORMAT31i241_tree);
//                        break;
//                    case 2:
//                        INSTRUCTION_FORMAT31i_OR_ID242 = (Token)this.match(this.input, 76, FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i3765);
//                        stream_INSTRUCTION_FORMAT31i_OR_ID.add(INSTRUCTION_FORMAT31i_OR_ID242);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(75, INSTRUCTION_FORMAT31i_OR_ID242));
//                        retval.tree = root_0;
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.instruction_return instruction() throws RecognitionException {
//        smaliParser.instruction_return retval = new smaliParser.instruction_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        ParserRuleReturnScope insn_format10t243 = null;
//        ParserRuleReturnScope insn_format10x244 = null;
//        ParserRuleReturnScope insn_format10x_odex245 = null;
//        ParserRuleReturnScope insn_format11n246 = null;
//        ParserRuleReturnScope insn_format11x247 = null;
//        ParserRuleReturnScope insn_format12x248 = null;
//        ParserRuleReturnScope insn_format20bc249 = null;
//        ParserRuleReturnScope insn_format20t250 = null;
//        ParserRuleReturnScope insn_format21c_field251 = null;
//        ParserRuleReturnScope insn_format21c_field_odex252 = null;
//        ParserRuleReturnScope insn_format21c_string253 = null;
//        ParserRuleReturnScope insn_format21c_type254 = null;
//        ParserRuleReturnScope insn_format21c_lambda255 = null;
//        ParserRuleReturnScope insn_format21c_method256 = null;
//        ParserRuleReturnScope insn_format21ih257 = null;
//        ParserRuleReturnScope insn_format21lh258 = null;
//        ParserRuleReturnScope insn_format21s259 = null;
//        ParserRuleReturnScope insn_format21t260 = null;
//        ParserRuleReturnScope insn_format22b261 = null;
//        ParserRuleReturnScope insn_format22c_field262 = null;
//        ParserRuleReturnScope insn_format22c_field_odex263 = null;
//        ParserRuleReturnScope insn_format22c_type264 = null;
//        ParserRuleReturnScope insn_format22c_string265 = null;
//        ParserRuleReturnScope insn_format22cs_field266 = null;
//        ParserRuleReturnScope insn_format22s267 = null;
//        ParserRuleReturnScope insn_format22t268 = null;
//        ParserRuleReturnScope insn_format22x269 = null;
//        ParserRuleReturnScope insn_format23x270 = null;
//        ParserRuleReturnScope insn_format25x271 = null;
//        ParserRuleReturnScope insn_format30t272 = null;
//        ParserRuleReturnScope insn_format31c273 = null;
//        ParserRuleReturnScope insn_format31i274 = null;
//        ParserRuleReturnScope insn_format31t275 = null;
//        ParserRuleReturnScope insn_format32x276 = null;
//        ParserRuleReturnScope insn_format35c_method277 = null;
//        ParserRuleReturnScope insn_format35c_type278 = null;
//        ParserRuleReturnScope insn_format35c_method_odex279 = null;
//        ParserRuleReturnScope insn_format35mi_method280 = null;
//        ParserRuleReturnScope insn_format35ms_method281 = null;
//        ParserRuleReturnScope insn_format3rc_method282 = null;
//        ParserRuleReturnScope insn_format3rc_method_odex283 = null;
//        ParserRuleReturnScope insn_format3rc_type284 = null;
//        ParserRuleReturnScope insn_format3rmi_method285 = null;
//        ParserRuleReturnScope insn_format3rms_method286 = null;
//        ParserRuleReturnScope insn_format51l287 = null;
//        ParserRuleReturnScope insn_array_data_directive288 = null;
//        ParserRuleReturnScope insn_packed_switch_directive289 = null;
//        smaliParser.insn_sparse_switch_directive_return insn_sparse_switch_directive290 = null;
//
//        try {
//            try {
//                byte alt51;
//                switch(this.input.LA(1)) {
//                    case 7:
//                        alt51 = 46;
//                        break;
//                    case 8:
//                    case 9:
//                    case 10:
//                    case 11:
//                    case 12:
//                    case 13:
//                    case 14:
//                    case 15:
//                    case 16:
//                    case 17:
//                    case 18:
//                    case 19:
//                    case 20:
//                    case 21:
//                    case 22:
//                    case 23:
//                    case 24:
//                    case 25:
//                    case 26:
//                    case 27:
//                    case 28:
//                    case 29:
//                    case 30:
//                    case 31:
//                    case 32:
//                    case 33:
//                    case 34:
//                    case 35:
//                    case 36:
//                    case 37:
//                    case 38:
//                    case 39:
//                    case 40:
//                    case 41:
//                    case 90:
//                    case 91:
//                    case 92:
//                    case 93:
//                    case 94:
//                    case 95:
//                    case 96:
//                    case 97:
//                    case 98:
//                    case 99:
//                    case 100:
//                    case 101:
//                    case 102:
//                    case 103:
//                    case 104:
//                    case 105:
//                    case 106:
//                    case 107:
//                    case 108:
//                    case 109:
//                    case 110:
//                    case 111:
//                    case 112:
//                    case 113:
//                    case 114:
//                    case 115:
//                    case 116:
//                    case 117:
//                    case 118:
//                    case 119:
//                    case 120:
//                    case 121:
//                    case 122:
//                    case 123:
//                    case 124:
//                    case 125:
//                    case 126:
//                    case 127:
//                    case 128:
//                    case 129:
//                    case 130:
//                    case 131:
//                    case 132:
//                    case 133:
//                    case 134:
//                    case 135:
//                    case 136:
//                    case 137:
//                    case 138:
//                    case 139:
//                    case 140:
//                    case 141:
//                    case 142:
//                    case 143:
//                    case 144:
//                    case 145:
//                    case 146:
//                    case 147:
//                    case 148:
//                    case 149:
//                    case 150:
//                    case 151:
//                    case 152:
//                    case 153:
//                    case 154:
//                    case 155:
//                    case 156:
//                    case 157:
//                    case 158:
//                    case 159:
//                    case 160:
//                    case 161:
//                    case 162:
//                    case 163:
//                    case 164:
//                    case 165:
//                    case 166:
//                    case 167:
//                    case 168:
//                    case 169:
//                    case 170:
//                    case 171:
//                    case 172:
//                    case 173:
//                    case 174:
//                    case 175:
//                    case 176:
//                    case 177:
//                    case 178:
//                    case 179:
//                    case 180:
//                    case 181:
//                    case 182:
//                    case 183:
//                    case 184:
//                    case 186:
//                    case 187:
//                    case 188:
//                    case 189:
//                    case 190:
//                    case 191:
//                    case 192:
//                    case 193:
//                    case 194:
//                    case 195:
//                    case 196:
//                    default:
//                        NoViableAltException nvae = new NoViableAltException("", 51, 0, this.input);
//                        throw nvae;
//                    case 42:
//                        alt51 = 1;
//                        break;
//                    case 43:
//                        alt51 = 2;
//                        break;
//                    case 44:
//                        alt51 = 3;
//                        break;
//                    case 45:
//                        alt51 = 4;
//                        break;
//                    case 46:
//                        alt51 = 5;
//                        break;
//                    case 47:
//                    case 48:
//                        alt51 = 6;
//                        break;
//                    case 49:
//                        alt51 = 7;
//                        break;
//                    case 50:
//                        alt51 = 8;
//                        break;
//                    case 51:
//                        alt51 = 9;
//                        break;
//                    case 52:
//                        alt51 = 10;
//                        break;
//                    case 53:
//                        alt51 = 13;
//                        break;
//                    case 54:
//                        alt51 = 14;
//                        break;
//                    case 55:
//                        alt51 = 11;
//                        break;
//                    case 56:
//                        alt51 = 12;
//                        break;
//                    case 57:
//                        alt51 = 15;
//                        break;
//                    case 58:
//                        alt51 = 16;
//                        break;
//                    case 59:
//                        alt51 = 17;
//                        break;
//                    case 60:
//                        alt51 = 18;
//                        break;
//                    case 61:
//                        alt51 = 19;
//                        break;
//                    case 62:
//                        alt51 = 20;
//                        break;
//                    case 63:
//                        alt51 = 21;
//                        break;
//                    case 64:
//                        alt51 = 23;
//                        break;
//                    case 65:
//                        alt51 = 22;
//                        break;
//                    case 66:
//                        alt51 = 24;
//                        break;
//                    case 67:
//                    case 68:
//                        alt51 = 25;
//                        break;
//                    case 69:
//                        alt51 = 26;
//                        break;
//                    case 70:
//                        alt51 = 27;
//                        break;
//                    case 71:
//                        alt51 = 28;
//                        break;
//                    case 72:
//                        alt51 = 29;
//                        break;
//                    case 73:
//                        alt51 = 30;
//                        break;
//                    case 74:
//                        alt51 = 31;
//                        break;
//                    case 75:
//                    case 76:
//                        alt51 = 32;
//                        break;
//                    case 77:
//                        alt51 = 33;
//                        break;
//                    case 78:
//                        alt51 = 34;
//                        break;
//                    case 79:
//                        alt51 = 35;
//                        break;
//                    case 80:
//                        alt51 = 37;
//                        break;
//                    case 81:
//                        alt51 = 36;
//                        break;
//                    case 82:
//                        alt51 = 38;
//                        break;
//                    case 83:
//                        alt51 = 39;
//                        break;
//                    case 84:
//                        alt51 = 40;
//                        break;
//                    case 85:
//                        alt51 = 41;
//                        break;
//                    case 86:
//                        alt51 = 42;
//                        break;
//                    case 87:
//                        alt51 = 43;
//                        break;
//                    case 88:
//                        alt51 = 44;
//                        break;
//                    case 89:
//                        alt51 = 45;
//                        break;
//                    case 185:
//                        alt51 = 47;
//                        break;
//                    case 197:
//                        alt51 = 48;
//                }
//
//                switch(alt51) {
//                    case 1:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format10t_in_instruction3782);
//                        insn_format10t243 = this.insn_format10t();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format10t243.getTree());
//                        break;
//                    case 2:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format10x_in_instruction3788);
//                        insn_format10x244 = this.insn_format10x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format10x244.getTree());
//                        break;
//                    case 3:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format10x_odex_in_instruction3794);
//                        insn_format10x_odex245 = this.insn_format10x_odex();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format10x_odex245.getTree());
//                        break;
//                    case 4:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format11n_in_instruction3800);
//                        insn_format11n246 = this.insn_format11n();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format11n246.getTree());
//                        break;
//                    case 5:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format11x_in_instruction3806);
//                        insn_format11x247 = this.insn_format11x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format11x247.getTree());
//                        break;
//                    case 6:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format12x_in_instruction3812);
//                        insn_format12x248 = this.insn_format12x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format12x248.getTree());
//                        break;
//                    case 7:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format20bc_in_instruction3818);
//                        insn_format20bc249 = this.insn_format20bc();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format20bc249.getTree());
//                        break;
//                    case 8:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format20t_in_instruction3824);
//                        insn_format20t250 = this.insn_format20t();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format20t250.getTree());
//                        break;
//                    case 9:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21c_field_in_instruction3830);
//                        insn_format21c_field251 = this.insn_format21c_field();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21c_field251.getTree());
//                        break;
//                    case 10:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21c_field_odex_in_instruction3836);
//                        insn_format21c_field_odex252 = this.insn_format21c_field_odex();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21c_field_odex252.getTree());
//                        break;
//                    case 11:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21c_string_in_instruction3842);
//                        insn_format21c_string253 = this.insn_format21c_string();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21c_string253.getTree());
//                        break;
//                    case 12:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21c_type_in_instruction3848);
//                        insn_format21c_type254 = this.insn_format21c_type();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21c_type254.getTree());
//                        break;
//                    case 13:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21c_lambda_in_instruction3854);
//                        insn_format21c_lambda255 = this.insn_format21c_lambda();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21c_lambda255.getTree());
//                        break;
//                    case 14:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21c_method_in_instruction3860);
//                        insn_format21c_method256 = this.insn_format21c_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21c_method256.getTree());
//                        break;
//                    case 15:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21ih_in_instruction3866);
//                        insn_format21ih257 = this.insn_format21ih();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21ih257.getTree());
//                        break;
//                    case 16:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21lh_in_instruction3872);
//                        insn_format21lh258 = this.insn_format21lh();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21lh258.getTree());
//                        break;
//                    case 17:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21s_in_instruction3878);
//                        insn_format21s259 = this.insn_format21s();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21s259.getTree());
//                        break;
//                    case 18:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format21t_in_instruction3884);
//                        insn_format21t260 = this.insn_format21t();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format21t260.getTree());
//                        break;
//                    case 19:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22b_in_instruction3890);
//                        insn_format22b261 = this.insn_format22b();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22b261.getTree());
//                        break;
//                    case 20:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22c_field_in_instruction3896);
//                        insn_format22c_field262 = this.insn_format22c_field();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22c_field262.getTree());
//                        break;
//                    case 21:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22c_field_odex_in_instruction3902);
//                        insn_format22c_field_odex263 = this.insn_format22c_field_odex();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22c_field_odex263.getTree());
//                        break;
//                    case 22:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22c_type_in_instruction3908);
//                        insn_format22c_type264 = this.insn_format22c_type();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22c_type264.getTree());
//                        break;
//                    case 23:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22c_string_in_instruction3914);
//                        insn_format22c_string265 = this.insn_format22c_string();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22c_string265.getTree());
//                        break;
//                    case 24:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22cs_field_in_instruction3920);
//                        insn_format22cs_field266 = this.insn_format22cs_field();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22cs_field266.getTree());
//                        break;
//                    case 25:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22s_in_instruction3926);
//                        insn_format22s267 = this.insn_format22s();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22s267.getTree());
//                        break;
//                    case 26:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22t_in_instruction3932);
//                        insn_format22t268 = this.insn_format22t();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22t268.getTree());
//                        break;
//                    case 27:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format22x_in_instruction3938);
//                        insn_format22x269 = this.insn_format22x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format22x269.getTree());
//                        break;
//                    case 28:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format23x_in_instruction3944);
//                        insn_format23x270 = this.insn_format23x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format23x270.getTree());
//                        break;
//                    case 29:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format25x_in_instruction3950);
//                        insn_format25x271 = this.insn_format25x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format25x271.getTree());
//                        break;
//                    case 30:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format30t_in_instruction3956);
//                        insn_format30t272 = this.insn_format30t();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format30t272.getTree());
//                        break;
//                    case 31:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format31c_in_instruction3962);
//                        insn_format31c273 = this.insn_format31c();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format31c273.getTree());
//                        break;
//                    case 32:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format31i_in_instruction3968);
//                        insn_format31i274 = this.insn_format31i();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format31i274.getTree());
//                        break;
//                    case 33:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format31t_in_instruction3974);
//                        insn_format31t275 = this.insn_format31t();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format31t275.getTree());
//                        break;
//                    case 34:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format32x_in_instruction3980);
//                        insn_format32x276 = this.insn_format32x();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format32x276.getTree());
//                        break;
//                    case 35:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format35c_method_in_instruction3986);
//                        insn_format35c_method277 = this.insn_format35c_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format35c_method277.getTree());
//                        break;
//                    case 36:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format35c_type_in_instruction3992);
//                        insn_format35c_type278 = this.insn_format35c_type();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format35c_type278.getTree());
//                        break;
//                    case 37:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format35c_method_odex_in_instruction3998);
//                        insn_format35c_method_odex279 = this.insn_format35c_method_odex();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format35c_method_odex279.getTree());
//                        break;
//                    case 38:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format35mi_method_in_instruction4004);
//                        insn_format35mi_method280 = this.insn_format35mi_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format35mi_method280.getTree());
//                        break;
//                    case 39:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format35ms_method_in_instruction4010);
//                        insn_format35ms_method281 = this.insn_format35ms_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format35ms_method281.getTree());
//                        break;
//                    case 40:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format3rc_method_in_instruction4016);
//                        insn_format3rc_method282 = this.insn_format3rc_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format3rc_method282.getTree());
//                        break;
//                    case 41:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format3rc_method_odex_in_instruction4022);
//                        insn_format3rc_method_odex283 = this.insn_format3rc_method_odex();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format3rc_method_odex283.getTree());
//                        break;
//                    case 42:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format3rc_type_in_instruction4028);
//                        insn_format3rc_type284 = this.insn_format3rc_type();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format3rc_type284.getTree());
//                        break;
//                    case 43:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format3rmi_method_in_instruction4034);
//                        insn_format3rmi_method285 = this.insn_format3rmi_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format3rmi_method285.getTree());
//                        break;
//                    case 44:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format3rms_method_in_instruction4040);
//                        insn_format3rms_method286 = this.insn_format3rms_method();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format3rms_method286.getTree());
//                        break;
//                    case 45:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_format51l_in_instruction4046);
//                        insn_format51l287 = this.insn_format51l();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_format51l287.getTree());
//                        break;
//                    case 46:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_array_data_directive_in_instruction4052);
//                        insn_array_data_directive288 = this.insn_array_data_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_array_data_directive288.getTree());
//                        break;
//                    case 47:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_packed_switch_directive_in_instruction4058);
//                        insn_packed_switch_directive289 = this.insn_packed_switch_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_packed_switch_directive289.getTree());
//                        break;
//                    case 48:
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        this.pushFollow(FOLLOW_insn_sparse_switch_directive_in_instruction4064);
//                        insn_sparse_switch_directive290 = this.insn_sparse_switch_directive();
//                        --this.state._fsp;
//                        this.adaptor.addChild(root_0, insn_sparse_switch_directive290.getTree());
//                }
//
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var56) {
//                this.reportError(var56);
//                this.recover(this.input, var56);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var56);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format10t_return insn_format10t() throws RecognitionException {
//        smaliParser.insn_format10t_return retval = new smaliParser.insn_format10t_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT10t291 = null;
//        ParserRuleReturnScope label_ref292 = null;
//        CommonTree INSTRUCTION_FORMAT10t291_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT10t");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT10t291 = (Token)this.match(this.input, 42, FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4084);
//                stream_INSTRUCTION_FORMAT10t.add(INSTRUCTION_FORMAT10t291);
//                this.pushFollow(FOLLOW_label_ref_in_insn_format10t4086);
//                label_ref292 = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(label_ref292.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(135, retval.start, "I_STATEMENT_FORMAT10t"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT10t.nextNode());
//                this.adaptor.addChild(root_1, stream_label_ref.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format10x_return insn_format10x() throws RecognitionException {
//        smaliParser.insn_format10x_return retval = new smaliParser.insn_format10x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT10x293 = null;
//        CommonTree INSTRUCTION_FORMAT10x293_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT10x");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT10x293 = (Token)this.match(this.input, 43, FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4116);
//                stream_INSTRUCTION_FORMAT10x.add(INSTRUCTION_FORMAT10x293);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(136, retval.start, "I_STATEMENT_FORMAT10x"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT10x.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var11) {
//                this.reportError(var11);
//                this.recover(this.input, var11);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format10x_odex_return insn_format10x_odex() throws RecognitionException {
//        smaliParser.insn_format10x_odex_return retval = new smaliParser.insn_format10x_odex_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT10x_ODEX294 = null;
//        CommonTree INSTRUCTION_FORMAT10x_ODEX294_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT10x_ODEX294 = (Token)this.match(this.input, 44, FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4144);
//                INSTRUCTION_FORMAT10x_ODEX294_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT10x_ODEX294);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT10x_ODEX294_tree);
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT10x_ODEX294 != null ? INSTRUCTION_FORMAT10x_ODEX294.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var9) {
//                this.reportError(var9);
//                this.recover(this.input, var9);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var9);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format11n_return insn_format11n() throws RecognitionException {
//        smaliParser.insn_format11n_return retval = new smaliParser.insn_format11n_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT11n295 = null;
//        Token REGISTER296 = null;
//        Token COMMA297 = null;
//        ParserRuleReturnScope integral_literal298 = null;
//        CommonTree INSTRUCTION_FORMAT11n295_tree = null;
//        CommonTree REGISTER296_tree = null;
//        CommonTree COMMA297_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11n = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT11n");
//        RewriteRuleSubtreeStream stream_integral_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule integral_literal");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT11n295 = (Token)this.match(this.input, 45, FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4165);
//                stream_INSTRUCTION_FORMAT11n.add(INSTRUCTION_FORMAT11n295);
//                REGISTER296 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format11n4167);
//                stream_REGISTER.add(REGISTER296);
//                COMMA297 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format11n4169);
//                stream_COMMA.add(COMMA297);
//                this.pushFollow(FOLLOW_integral_literal_in_insn_format11n4171);
//                integral_literal298 = this.integral_literal();
//                --this.state._fsp;
//                stream_integral_literal.add(integral_literal298.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(137, retval.start, "I_STATEMENT_FORMAT11n"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT11n.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_integral_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format11x_return insn_format11x() throws RecognitionException {
//        smaliParser.insn_format11x_return retval = new smaliParser.insn_format11x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT11x299 = null;
//        Token REGISTER300 = null;
//        CommonTree INSTRUCTION_FORMAT11x299_tree = null;
//        CommonTree REGISTER300_tree = null;
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT11x");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT11x299 = (Token)this.match(this.input, 46, FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4203);
//                stream_INSTRUCTION_FORMAT11x.add(INSTRUCTION_FORMAT11x299);
//                REGISTER300 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format11x4205);
//                stream_REGISTER.add(REGISTER300);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(138, retval.start, "I_STATEMENT_FORMAT11x"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT11x.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var14) {
//                this.reportError(var14);
//                this.recover(this.input, var14);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format12x_return insn_format12x() throws RecognitionException {
//        smaliParser.insn_format12x_return retval = new smaliParser.insn_format12x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token REGISTER302 = null;
//        Token COMMA303 = null;
//        Token REGISTER304 = null;
//        ParserRuleReturnScope instruction_format12x301 = null;
//        CommonTree REGISTER302_tree = null;
//        CommonTree COMMA303_tree = null;
//        CommonTree REGISTER304_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleSubtreeStream stream_instruction_format12x = new RewriteRuleSubtreeStream(this.adaptor, "rule instruction_format12x");
//
//        try {
//            try {
//                this.pushFollow(FOLLOW_instruction_format12x_in_insn_format12x4235);
//                instruction_format12x301 = this.instruction_format12x();
//                --this.state._fsp;
//                stream_instruction_format12x.add(instruction_format12x301.getTree());
//                REGISTER302 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format12x4237);
//                stream_REGISTER.add(REGISTER302);
//                COMMA303 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format12x4239);
//                stream_COMMA.add(COMMA303);
//                REGISTER304 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format12x4241);
//                stream_REGISTER.add(REGISTER304);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(139, retval.start, "I_STATEMENT_FORMAT12x"), root_1);
//                this.adaptor.addChild(root_1, stream_instruction_format12x.nextTree());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format20bc_return insn_format20bc() throws RecognitionException {
//        smaliParser.insn_format20bc_return retval = new smaliParser.insn_format20bc_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT20bc305 = null;
//        Token VERIFICATION_ERROR_TYPE306 = null;
//        Token COMMA307 = null;
//        ParserRuleReturnScope verification_error_reference308 = null;
//        CommonTree INSTRUCTION_FORMAT20bc305_tree = null;
//        CommonTree VERIFICATION_ERROR_TYPE306_tree = null;
//        CommonTree COMMA307_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_VERIFICATION_ERROR_TYPE = new RewriteRuleTokenStream(this.adaptor, "token VERIFICATION_ERROR_TYPE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT20bc = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT20bc");
//        RewriteRuleSubtreeStream stream_verification_error_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule verification_error_reference");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT20bc305 = (Token)this.match(this.input, 49, FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4273);
//                stream_INSTRUCTION_FORMAT20bc.add(INSTRUCTION_FORMAT20bc305);
//                VERIFICATION_ERROR_TYPE306 = (Token)this.match(this.input, 201, FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4275);
//                stream_VERIFICATION_ERROR_TYPE.add(VERIFICATION_ERROR_TYPE306);
//                COMMA307 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format20bc4277);
//                stream_COMMA.add(COMMA307);
//                this.pushFollow(FOLLOW_verification_error_reference_in_insn_format20bc4279);
//                verification_error_reference308 = this.verification_error_reference();
//                --this.state._fsp;
//                stream_verification_error_reference.add(verification_error_reference308.getTree());
//                if (!this.allowOdex || this.opcodes.getOpcodeByName(INSTRUCTION_FORMAT20bc305 != null ? INSTRUCTION_FORMAT20bc305.getText() : null) == null || this.apiLevel >= 14) {
//                    this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT20bc305 != null ? INSTRUCTION_FORMAT20bc305.getText() : null);
//                }
//
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(140, "I_STATEMENT_FORMAT20bc"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT20bc.nextNode());
//                this.adaptor.addChild(root_1, stream_VERIFICATION_ERROR_TYPE.nextNode());
//                this.adaptor.addChild(root_1, stream_verification_error_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format20t_return insn_format20t() throws RecognitionException {
//        smaliParser.insn_format20t_return retval = new smaliParser.insn_format20t_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT20t309 = null;
//        ParserRuleReturnScope label_ref310 = null;
//        CommonTree INSTRUCTION_FORMAT20t309_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT20t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT20t");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT20t309 = (Token)this.match(this.input, 50, FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4316);
//                stream_INSTRUCTION_FORMAT20t.add(INSTRUCTION_FORMAT20t309);
//                this.pushFollow(FOLLOW_label_ref_in_insn_format20t4318);
//                label_ref310 = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(label_ref310.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(141, retval.start, "I_STATEMENT_FORMAT20t"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT20t.nextNode());
//                this.adaptor.addChild(root_1, stream_label_ref.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21c_field_return insn_format21c_field() throws RecognitionException {
//        smaliParser.insn_format21c_field_return retval = new smaliParser.insn_format21c_field_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21c_FIELD311 = null;
//        Token REGISTER312 = null;
//        Token COMMA313 = null;
//        ParserRuleReturnScope field_reference314 = null;
//        CommonTree INSTRUCTION_FORMAT21c_FIELD311_tree = null;
//        CommonTree REGISTER312_tree = null;
//        CommonTree COMMA313_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_FIELD");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleSubtreeStream stream_field_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule field_reference");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21c_FIELD311 = (Token)this.match(this.input, 51, FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4348);
//                stream_INSTRUCTION_FORMAT21c_FIELD.add(INSTRUCTION_FORMAT21c_FIELD311);
//                REGISTER312 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21c_field4350);
//                stream_REGISTER.add(REGISTER312);
//                COMMA313 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21c_field4352);
//                stream_COMMA.add(COMMA313);
//                this.pushFollow(FOLLOW_field_reference_in_insn_format21c_field4354);
//                field_reference314 = this.field_reference();
//                --this.state._fsp;
//                stream_field_reference.add(field_reference314.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(142, retval.start, "I_STATEMENT_FORMAT21c_FIELD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_FIELD.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_field_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21c_field_odex_return insn_format21c_field_odex() throws RecognitionException {
//        smaliParser.insn_format21c_field_odex_return retval = new smaliParser.insn_format21c_field_odex_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21c_FIELD_ODEX315 = null;
//        Token REGISTER316 = null;
//        Token COMMA317 = null;
//        ParserRuleReturnScope field_reference318 = null;
//        CommonTree INSTRUCTION_FORMAT21c_FIELD_ODEX315_tree = null;
//        CommonTree REGISTER316_tree = null;
//        CommonTree COMMA317_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD_ODEX = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_FIELD_ODEX");
//        RewriteRuleSubtreeStream stream_field_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule field_reference");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21c_FIELD_ODEX315 = (Token)this.match(this.input, 52, FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4386);
//                stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.add(INSTRUCTION_FORMAT21c_FIELD_ODEX315);
//                REGISTER316 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21c_field_odex4388);
//                stream_REGISTER.add(REGISTER316);
//                COMMA317 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21c_field_odex4390);
//                stream_COMMA.add(COMMA317);
//                this.pushFollow(FOLLOW_field_reference_in_insn_format21c_field_odex4392);
//                field_reference318 = this.field_reference();
//                --this.state._fsp;
//                stream_field_reference.add(field_reference318.getTree());
//                if (!this.allowOdex || this.opcodes.getOpcodeByName(INSTRUCTION_FORMAT21c_FIELD_ODEX315 != null ? INSTRUCTION_FORMAT21c_FIELD_ODEX315.getText() : null) == null || this.apiLevel >= 14) {
//                    this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT21c_FIELD_ODEX315 != null ? INSTRUCTION_FORMAT21c_FIELD_ODEX315.getText() : null);
//                }
//
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(142, retval.start, "I_STATEMENT_FORMAT21c_FIELD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_field_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21c_string_return insn_format21c_string() throws RecognitionException {
//        smaliParser.insn_format21c_string_return retval = new smaliParser.insn_format21c_string_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21c_STRING319 = null;
//        Token REGISTER320 = null;
//        Token COMMA321 = null;
//        Token STRING_LITERAL322 = null;
//        CommonTree INSTRUCTION_FORMAT21c_STRING319_tree = null;
//        CommonTree REGISTER320_tree = null;
//        CommonTree COMMA321_tree = null;
//        CommonTree STRING_LITERAL322_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_STRING = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_STRING");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21c_STRING319 = (Token)this.match(this.input, 55, FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4430);
//                stream_INSTRUCTION_FORMAT21c_STRING.add(INSTRUCTION_FORMAT21c_STRING319);
//                REGISTER320 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21c_string4432);
//                stream_REGISTER.add(REGISTER320);
//                COMMA321 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21c_string4434);
//                stream_COMMA.add(COMMA321);
//                STRING_LITERAL322 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_insn_format21c_string4436);
//                stream_STRING_LITERAL.add(STRING_LITERAL322);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(145, retval.start, "I_STATEMENT_FORMAT21c_STRING"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_STRING.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var20) {
//                this.reportError(var20);
//                this.recover(this.input, var20);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21c_type_return insn_format21c_type() throws RecognitionException {
//        smaliParser.insn_format21c_type_return retval = new smaliParser.insn_format21c_type_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21c_TYPE323 = null;
//        Token REGISTER324 = null;
//        Token COMMA325 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor326 = null;
//        CommonTree INSTRUCTION_FORMAT21c_TYPE323_tree = null;
//        CommonTree REGISTER324_tree = null;
//        CommonTree COMMA325_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_TYPE");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21c_TYPE323 = (Token)this.match(this.input, 56, FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4468);
//                stream_INSTRUCTION_FORMAT21c_TYPE.add(INSTRUCTION_FORMAT21c_TYPE323);
//                REGISTER324 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21c_type4470);
//                stream_REGISTER.add(REGISTER324);
//                COMMA325 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21c_type4472);
//                stream_COMMA.add(COMMA325);
//                this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4474);
//                nonvoid_type_descriptor326 = this.nonvoid_type_descriptor();
//                --this.state._fsp;
//                stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor326.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(146, retval.start, "I_STATEMENT_FORMAT21c"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_TYPE.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21c_lambda_return insn_format21c_lambda() throws RecognitionException {
//        smaliParser.insn_format21c_lambda_return retval = new smaliParser.insn_format21c_lambda_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21c_LAMBDA327 = null;
//        Token REGISTER328 = null;
//        Token COMMA329 = null;
//        Token STRING_LITERAL330 = null;
//        CommonTree INSTRUCTION_FORMAT21c_LAMBDA327_tree = null;
//        CommonTree REGISTER328_tree = null;
//        CommonTree COMMA329_tree = null;
//        CommonTree STRING_LITERAL330_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_LAMBDA = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_LAMBDA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21c_LAMBDA327 = (Token)this.match(this.input, 53, FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda4506);
//                stream_INSTRUCTION_FORMAT21c_LAMBDA.add(INSTRUCTION_FORMAT21c_LAMBDA327);
//                REGISTER328 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21c_lambda4508);
//                stream_REGISTER.add(REGISTER328);
//                COMMA329 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21c_lambda4510);
//                stream_COMMA.add(COMMA329);
//                STRING_LITERAL330 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_insn_format21c_lambda4512);
//                stream_STRING_LITERAL.add(STRING_LITERAL330);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(143, retval.start, "I_STATEMENT_FORMAT21c_LAMBDA"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_LAMBDA.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var20) {
//                this.reportError(var20);
//                this.recover(this.input, var20);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21c_method_return insn_format21c_method() throws RecognitionException {
//        smaliParser.insn_format21c_method_return retval = new smaliParser.insn_format21c_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21c_METHOD331 = null;
//        Token REGISTER332 = null;
//        Token COMMA333 = null;
//        ParserRuleReturnScope method_reference334 = null;
//        CommonTree INSTRUCTION_FORMAT21c_METHOD331_tree = null;
//        CommonTree REGISTER332_tree = null;
//        CommonTree COMMA333_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21c_METHOD");
//        RewriteRuleSubtreeStream stream_method_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule method_reference");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21c_METHOD331 = (Token)this.match(this.input, 54, FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method4544);
//                stream_INSTRUCTION_FORMAT21c_METHOD.add(INSTRUCTION_FORMAT21c_METHOD331);
//                REGISTER332 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21c_method4546);
//                stream_REGISTER.add(REGISTER332);
//                COMMA333 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21c_method4548);
//                stream_COMMA.add(COMMA333);
//                this.pushFollow(FOLLOW_method_reference_in_insn_format21c_method4550);
//                method_reference334 = this.method_reference();
//                --this.state._fsp;
//                stream_method_reference.add(method_reference334.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(144, retval.start, "I_STATEMENT_FORMAT21c_METHOD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_METHOD.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_method_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21ih_return insn_format21ih() throws RecognitionException {
//        smaliParser.insn_format21ih_return retval = new smaliParser.insn_format21ih_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21ih335 = null;
//        Token REGISTER336 = null;
//        Token COMMA337 = null;
//        ParserRuleReturnScope fixed_32bit_literal338 = null;
//        CommonTree INSTRUCTION_FORMAT21ih335_tree = null;
//        CommonTree REGISTER336_tree = null;
//        CommonTree COMMA337_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21ih = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21ih");
//        RewriteRuleSubtreeStream stream_fixed_32bit_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_32bit_literal");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21ih335 = (Token)this.match(this.input, 57, FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4582);
//                stream_INSTRUCTION_FORMAT21ih.add(INSTRUCTION_FORMAT21ih335);
//                REGISTER336 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21ih4584);
//                stream_REGISTER.add(REGISTER336);
//                COMMA337 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21ih4586);
//                stream_COMMA.add(COMMA337);
//                this.pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21ih4588);
//                fixed_32bit_literal338 = this.fixed_32bit_literal();
//                --this.state._fsp;
//                stream_fixed_32bit_literal.add(fixed_32bit_literal338.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(147, retval.start, "I_STATEMENT_FORMAT21ih"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21ih.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21lh_return insn_format21lh() throws RecognitionException {
//        smaliParser.insn_format21lh_return retval = new smaliParser.insn_format21lh_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21lh339 = null;
//        Token REGISTER340 = null;
//        Token COMMA341 = null;
//        ParserRuleReturnScope fixed_32bit_literal342 = null;
//        CommonTree INSTRUCTION_FORMAT21lh339_tree = null;
//        CommonTree REGISTER340_tree = null;
//        CommonTree COMMA341_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21lh = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21lh");
//        RewriteRuleSubtreeStream stream_fixed_32bit_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_32bit_literal");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21lh339 = (Token)this.match(this.input, 58, FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4620);
//                stream_INSTRUCTION_FORMAT21lh.add(INSTRUCTION_FORMAT21lh339);
//                REGISTER340 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21lh4622);
//                stream_REGISTER.add(REGISTER340);
//                COMMA341 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21lh4624);
//                stream_COMMA.add(COMMA341);
//                this.pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21lh4626);
//                fixed_32bit_literal342 = this.fixed_32bit_literal();
//                --this.state._fsp;
//                stream_fixed_32bit_literal.add(fixed_32bit_literal342.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(148, retval.start, "I_STATEMENT_FORMAT21lh"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21lh.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21s_return insn_format21s() throws RecognitionException {
//        smaliParser.insn_format21s_return retval = new smaliParser.insn_format21s_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21s343 = null;
//        Token REGISTER344 = null;
//        Token COMMA345 = null;
//        ParserRuleReturnScope integral_literal346 = null;
//        CommonTree INSTRUCTION_FORMAT21s343_tree = null;
//        CommonTree REGISTER344_tree = null;
//        CommonTree COMMA345_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21s = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21s");
//        RewriteRuleSubtreeStream stream_integral_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule integral_literal");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21s343 = (Token)this.match(this.input, 59, FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s4658);
//                stream_INSTRUCTION_FORMAT21s.add(INSTRUCTION_FORMAT21s343);
//                REGISTER344 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21s4660);
//                stream_REGISTER.add(REGISTER344);
//                COMMA345 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21s4662);
//                stream_COMMA.add(COMMA345);
//                this.pushFollow(FOLLOW_integral_literal_in_insn_format21s4664);
//                integral_literal346 = this.integral_literal();
//                --this.state._fsp;
//                stream_integral_literal.add(integral_literal346.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(149, retval.start, "I_STATEMENT_FORMAT21s"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21s.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_integral_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format21t_return insn_format21t() throws RecognitionException {
//        smaliParser.insn_format21t_return retval = new smaliParser.insn_format21t_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT21t347 = null;
//        Token REGISTER348 = null;
//        Token COMMA349 = null;
//        ParserRuleReturnScope label_ref350 = null;
//        CommonTree INSTRUCTION_FORMAT21t347_tree = null;
//        CommonTree REGISTER348_tree = null;
//        CommonTree COMMA349_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT21t");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT21t347 = (Token)this.match(this.input, 60, FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t4696);
//                stream_INSTRUCTION_FORMAT21t.add(INSTRUCTION_FORMAT21t347);
//                REGISTER348 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format21t4698);
//                stream_REGISTER.add(REGISTER348);
//                COMMA349 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format21t4700);
//                stream_COMMA.add(COMMA349);
//                this.pushFollow(FOLLOW_label_ref_in_insn_format21t4702);
//                label_ref350 = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(label_ref350.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(150, retval.start, "I_STATEMENT_FORMAT21t"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21t.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_label_ref.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22b_return insn_format22b() throws RecognitionException {
//        smaliParser.insn_format22b_return retval = new smaliParser.insn_format22b_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22b351 = null;
//        Token REGISTER352 = null;
//        Token COMMA353 = null;
//        Token REGISTER354 = null;
//        Token COMMA355 = null;
//        ParserRuleReturnScope integral_literal356 = null;
//        CommonTree INSTRUCTION_FORMAT22b351_tree = null;
//        CommonTree REGISTER352_tree = null;
//        CommonTree COMMA353_tree = null;
//        CommonTree REGISTER354_tree = null;
//        CommonTree COMMA355_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22b = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22b");
//        RewriteRuleSubtreeStream stream_integral_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule integral_literal");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22b351 = (Token)this.match(this.input, 61, FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b4734);
//                stream_INSTRUCTION_FORMAT22b.add(INSTRUCTION_FORMAT22b351);
//                REGISTER352 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22b4736);
//                stream_REGISTER.add(REGISTER352);
//                COMMA353 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22b4738);
//                stream_COMMA.add(COMMA353);
//                REGISTER354 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22b4740);
//                stream_REGISTER.add(REGISTER354);
//                COMMA355 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22b4742);
//                stream_COMMA.add(COMMA355);
//                this.pushFollow(FOLLOW_integral_literal_in_insn_format22b4744);
//                integral_literal356 = this.integral_literal();
//                --this.state._fsp;
//                stream_integral_literal.add(integral_literal356.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(151, retval.start, "I_STATEMENT_FORMAT22b"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22b.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_integral_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var23) {
//                this.reportError(var23);
//                this.recover(this.input, var23);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22c_field_return insn_format22c_field() throws RecognitionException {
//        smaliParser.insn_format22c_field_return retval = new smaliParser.insn_format22c_field_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22c_FIELD357 = null;
//        Token REGISTER358 = null;
//        Token COMMA359 = null;
//        Token REGISTER360 = null;
//        Token COMMA361 = null;
//        ParserRuleReturnScope field_reference362 = null;
//        CommonTree INSTRUCTION_FORMAT22c_FIELD357_tree = null;
//        CommonTree REGISTER358_tree = null;
//        CommonTree COMMA359_tree = null;
//        CommonTree REGISTER360_tree = null;
//        CommonTree COMMA361_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_FIELD");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleSubtreeStream stream_field_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule field_reference");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22c_FIELD357 = (Token)this.match(this.input, 62, FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field4778);
//                stream_INSTRUCTION_FORMAT22c_FIELD.add(INSTRUCTION_FORMAT22c_FIELD357);
//                REGISTER358 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_field4780);
//                stream_REGISTER.add(REGISTER358);
//                COMMA359 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_field4782);
//                stream_COMMA.add(COMMA359);
//                REGISTER360 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_field4784);
//                stream_REGISTER.add(REGISTER360);
//                COMMA361 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_field4786);
//                stream_COMMA.add(COMMA361);
//                this.pushFollow(FOLLOW_field_reference_in_insn_format22c_field4788);
//                field_reference362 = this.field_reference();
//                --this.state._fsp;
//                stream_field_reference.add(field_reference362.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(152, retval.start, "I_STATEMENT_FORMAT22c_FIELD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_FIELD.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_field_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var23) {
//                this.reportError(var23);
//                this.recover(this.input, var23);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22c_field_odex_return insn_format22c_field_odex() throws RecognitionException {
//        smaliParser.insn_format22c_field_odex_return retval = new smaliParser.insn_format22c_field_odex_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22c_FIELD_ODEX363 = null;
//        Token REGISTER364 = null;
//        Token COMMA365 = null;
//        Token REGISTER366 = null;
//        Token COMMA367 = null;
//        ParserRuleReturnScope field_reference368 = null;
//        CommonTree INSTRUCTION_FORMAT22c_FIELD_ODEX363_tree = null;
//        CommonTree REGISTER364_tree = null;
//        CommonTree COMMA365_tree = null;
//        CommonTree REGISTER366_tree = null;
//        CommonTree COMMA367_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD_ODEX = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_FIELD_ODEX");
//        RewriteRuleSubtreeStream stream_field_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule field_reference");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22c_FIELD_ODEX363 = (Token)this.match(this.input, 63, FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex4822);
//                stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.add(INSTRUCTION_FORMAT22c_FIELD_ODEX363);
//                REGISTER364 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_field_odex4824);
//                stream_REGISTER.add(REGISTER364);
//                COMMA365 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_field_odex4826);
//                stream_COMMA.add(COMMA365);
//                REGISTER366 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_field_odex4828);
//                stream_REGISTER.add(REGISTER366);
//                COMMA367 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_field_odex4830);
//                stream_COMMA.add(COMMA367);
//                this.pushFollow(FOLLOW_field_reference_in_insn_format22c_field_odex4832);
//                field_reference368 = this.field_reference();
//                --this.state._fsp;
//                stream_field_reference.add(field_reference368.getTree());
//                if (!this.allowOdex || this.opcodes.getOpcodeByName(INSTRUCTION_FORMAT22c_FIELD_ODEX363 != null ? INSTRUCTION_FORMAT22c_FIELD_ODEX363.getText() : null) == null || this.apiLevel >= 14) {
//                    this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT22c_FIELD_ODEX363 != null ? INSTRUCTION_FORMAT22c_FIELD_ODEX363.getText() : null);
//                }
//
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(152, retval.start, "I_STATEMENT_FORMAT22c_FIELD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_field_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var23) {
//                this.reportError(var23);
//                this.recover(this.input, var23);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22c_type_return insn_format22c_type() throws RecognitionException {
//        smaliParser.insn_format22c_type_return retval = new smaliParser.insn_format22c_type_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22c_TYPE369 = null;
//        Token REGISTER370 = null;
//        Token COMMA371 = null;
//        Token REGISTER372 = null;
//        Token COMMA373 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor374 = null;
//        CommonTree INSTRUCTION_FORMAT22c_TYPE369_tree = null;
//        CommonTree REGISTER370_tree = null;
//        CommonTree COMMA371_tree = null;
//        CommonTree REGISTER372_tree = null;
//        CommonTree COMMA373_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_TYPE");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22c_TYPE369 = (Token)this.match(this.input, 65, FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type4872);
//                stream_INSTRUCTION_FORMAT22c_TYPE.add(INSTRUCTION_FORMAT22c_TYPE369);
//                REGISTER370 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_type4874);
//                stream_REGISTER.add(REGISTER370);
//                COMMA371 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_type4876);
//                stream_COMMA.add(COMMA371);
//                REGISTER372 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_type4878);
//                stream_REGISTER.add(REGISTER372);
//                COMMA373 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_type4880);
//                stream_COMMA.add(COMMA373);
//                this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type4882);
//                nonvoid_type_descriptor374 = this.nonvoid_type_descriptor();
//                --this.state._fsp;
//                stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor374.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(154, retval.start, "I_STATEMENT_FORMAT22c_TYPE"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_TYPE.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var23) {
//                this.reportError(var23);
//                this.recover(this.input, var23);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22c_string_return insn_format22c_string() throws RecognitionException {
//        smaliParser.insn_format22c_string_return retval = new smaliParser.insn_format22c_string_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22c_STRING375 = null;
//        Token REGISTER376 = null;
//        Token COMMA377 = null;
//        Token REGISTER378 = null;
//        Token COMMA379 = null;
//        Token STRING_LITERAL380 = null;
//        CommonTree INSTRUCTION_FORMAT22c_STRING375_tree = null;
//        CommonTree REGISTER376_tree = null;
//        CommonTree COMMA377_tree = null;
//        CommonTree REGISTER378_tree = null;
//        CommonTree COMMA379_tree = null;
//        CommonTree STRING_LITERAL380_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_STRING = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22c_STRING");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22c_STRING375 = (Token)this.match(this.input, 64, FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string4916);
//                stream_INSTRUCTION_FORMAT22c_STRING.add(INSTRUCTION_FORMAT22c_STRING375);
//                REGISTER376 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_string4918);
//                stream_REGISTER.add(REGISTER376);
//                COMMA377 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_string4920);
//                stream_COMMA.add(COMMA377);
//                REGISTER378 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22c_string4922);
//                stream_REGISTER.add(REGISTER378);
//                COMMA379 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22c_string4924);
//                stream_COMMA.add(COMMA379);
//                STRING_LITERAL380 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_insn_format22c_string4926);
//                stream_STRING_LITERAL.add(STRING_LITERAL380);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(153, retval.start, "I_STATEMENT_FORMAT22c_STRING"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_STRING.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var24) {
//                this.reportError(var24);
//                this.recover(this.input, var24);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var24);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22cs_field_return insn_format22cs_field() throws RecognitionException {
//        smaliParser.insn_format22cs_field_return retval = new smaliParser.insn_format22cs_field_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22cs_FIELD381 = null;
//        Token REGISTER382 = null;
//        Token COMMA383 = null;
//        Token REGISTER384 = null;
//        Token COMMA385 = null;
//        Token FIELD_OFFSET386 = null;
//        CommonTree INSTRUCTION_FORMAT22cs_FIELD381_tree = null;
//        CommonTree REGISTER382_tree = null;
//        CommonTree COMMA383_tree = null;
//        CommonTree REGISTER384_tree = null;
//        CommonTree COMMA385_tree = null;
//        CommonTree FIELD_OFFSET386_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT22cs_FIELD381 = (Token)this.match(this.input, 66, FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field4960);
//                INSTRUCTION_FORMAT22cs_FIELD381_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT22cs_FIELD381);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT22cs_FIELD381_tree);
//                REGISTER382 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22cs_field4962);
//                REGISTER382_tree = (CommonTree)this.adaptor.create(REGISTER382);
//                this.adaptor.addChild(root_0, REGISTER382_tree);
//                COMMA383 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22cs_field4964);
//                COMMA383_tree = (CommonTree)this.adaptor.create(COMMA383);
//                this.adaptor.addChild(root_0, COMMA383_tree);
//                REGISTER384 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22cs_field4966);
//                REGISTER384_tree = (CommonTree)this.adaptor.create(REGISTER384);
//                this.adaptor.addChild(root_0, REGISTER384_tree);
//                COMMA385 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22cs_field4968);
//                COMMA385_tree = (CommonTree)this.adaptor.create(COMMA385);
//                this.adaptor.addChild(root_0, COMMA385_tree);
//                FIELD_OFFSET386 = (Token)this.match(this.input, 37, FOLLOW_FIELD_OFFSET_in_insn_format22cs_field4970);
//                FIELD_OFFSET386_tree = (CommonTree)this.adaptor.create(FIELD_OFFSET386);
//                this.adaptor.addChild(root_0, FIELD_OFFSET386_tree);
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT22cs_FIELD381 != null ? INSTRUCTION_FORMAT22cs_FIELD381.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22s_return insn_format22s() throws RecognitionException {
//        smaliParser.insn_format22s_return retval = new smaliParser.insn_format22s_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token REGISTER388 = null;
//        Token COMMA389 = null;
//        Token REGISTER390 = null;
//        Token COMMA391 = null;
//        ParserRuleReturnScope instruction_format22s387 = null;
//        ParserRuleReturnScope integral_literal392 = null;
//        CommonTree REGISTER388_tree = null;
//        CommonTree COMMA389_tree = null;
//        CommonTree REGISTER390_tree = null;
//        CommonTree COMMA391_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleSubtreeStream stream_instruction_format22s = new RewriteRuleSubtreeStream(this.adaptor, "rule instruction_format22s");
//        RewriteRuleSubtreeStream stream_integral_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule integral_literal");
//
//        try {
//            try {
//                this.pushFollow(FOLLOW_instruction_format22s_in_insn_format22s4991);
//                instruction_format22s387 = this.instruction_format22s();
//                --this.state._fsp;
//                stream_instruction_format22s.add(instruction_format22s387.getTree());
//                REGISTER388 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22s4993);
//                stream_REGISTER.add(REGISTER388);
//                COMMA389 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22s4995);
//                stream_COMMA.add(COMMA389);
//                REGISTER390 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22s4997);
//                stream_REGISTER.add(REGISTER390);
//                COMMA391 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22s4999);
//                stream_COMMA.add(COMMA391);
//                this.pushFollow(FOLLOW_integral_literal_in_insn_format22s5001);
//                integral_literal392 = this.integral_literal();
//                --this.state._fsp;
//                stream_integral_literal.add(integral_literal392.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(155, retval.start, "I_STATEMENT_FORMAT22s"), root_1);
//                this.adaptor.addChild(root_1, stream_instruction_format22s.nextTree());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_integral_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var22) {
//                this.reportError(var22);
//                this.recover(this.input, var22);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var22);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22t_return insn_format22t() throws RecognitionException {
//        smaliParser.insn_format22t_return retval = new smaliParser.insn_format22t_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22t393 = null;
//        Token REGISTER394 = null;
//        Token COMMA395 = null;
//        Token REGISTER396 = null;
//        Token COMMA397 = null;
//        ParserRuleReturnScope label_ref398 = null;
//        CommonTree INSTRUCTION_FORMAT22t393_tree = null;
//        CommonTree REGISTER394_tree = null;
//        CommonTree COMMA395_tree = null;
//        CommonTree REGISTER396_tree = null;
//        CommonTree COMMA397_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22t");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22t393 = (Token)this.match(this.input, 69, FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5035);
//                stream_INSTRUCTION_FORMAT22t.add(INSTRUCTION_FORMAT22t393);
//                REGISTER394 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22t5037);
//                stream_REGISTER.add(REGISTER394);
//                COMMA395 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22t5039);
//                stream_COMMA.add(COMMA395);
//                REGISTER396 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22t5041);
//                stream_REGISTER.add(REGISTER396);
//                COMMA397 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22t5043);
//                stream_COMMA.add(COMMA397);
//                this.pushFollow(FOLLOW_label_ref_in_insn_format22t5045);
//                label_ref398 = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(label_ref398.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(156, retval.start, "I_STATEMENT_FFORMAT22t"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22t.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_label_ref.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var23) {
//                this.reportError(var23);
//                this.recover(this.input, var23);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format22x_return insn_format22x() throws RecognitionException {
//        smaliParser.insn_format22x_return retval = new smaliParser.insn_format22x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT22x399 = null;
//        Token REGISTER400 = null;
//        Token COMMA401 = null;
//        Token REGISTER402 = null;
//        CommonTree INSTRUCTION_FORMAT22x399_tree = null;
//        CommonTree REGISTER400_tree = null;
//        CommonTree COMMA401_tree = null;
//        CommonTree REGISTER402_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT22x");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT22x399 = (Token)this.match(this.input, 70, FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5079);
//                stream_INSTRUCTION_FORMAT22x.add(INSTRUCTION_FORMAT22x399);
//                REGISTER400 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22x5081);
//                stream_REGISTER.add(REGISTER400);
//                COMMA401 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format22x5083);
//                stream_COMMA.add(COMMA401);
//                REGISTER402 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format22x5085);
//                stream_REGISTER.add(REGISTER402);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(157, retval.start, "I_STATEMENT_FORMAT22x"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22x.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format23x_return insn_format23x() throws RecognitionException {
//        smaliParser.insn_format23x_return retval = new smaliParser.insn_format23x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT23x403 = null;
//        Token REGISTER404 = null;
//        Token COMMA405 = null;
//        Token REGISTER406 = null;
//        Token COMMA407 = null;
//        Token REGISTER408 = null;
//        CommonTree INSTRUCTION_FORMAT23x403_tree = null;
//        CommonTree REGISTER404_tree = null;
//        CommonTree COMMA405_tree = null;
//        CommonTree REGISTER406_tree = null;
//        CommonTree COMMA407_tree = null;
//        CommonTree REGISTER408_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT23x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT23x");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT23x403 = (Token)this.match(this.input, 71, FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5117);
//                stream_INSTRUCTION_FORMAT23x.add(INSTRUCTION_FORMAT23x403);
//                REGISTER404 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format23x5119);
//                stream_REGISTER.add(REGISTER404);
//                COMMA405 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format23x5121);
//                stream_COMMA.add(COMMA405);
//                REGISTER406 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format23x5123);
//                stream_REGISTER.add(REGISTER406);
//                COMMA407 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format23x5125);
//                stream_COMMA.add(COMMA407);
//                REGISTER408 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format23x5127);
//                stream_REGISTER.add(REGISTER408);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(158, retval.start, "I_STATEMENT_FORMAT23x"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT23x.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var23) {
//                this.reportError(var23);
//                this.recover(this.input, var23);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format25x_return insn_format25x() throws RecognitionException {
//        smaliParser.insn_format25x_return retval = new smaliParser.insn_format25x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT25x409 = null;
//        Token REGISTER410 = null;
//        Token COMMA411 = null;
//        Token OPEN_BRACE412 = null;
//        Token CLOSE_BRACE414 = null;
//        ParserRuleReturnScope register_list413 = null;
//        CommonTree INSTRUCTION_FORMAT25x409_tree = null;
//        CommonTree REGISTER410_tree = null;
//        CommonTree COMMA411_tree = null;
//        CommonTree OPEN_BRACE412_tree = null;
//        CommonTree CLOSE_BRACE414_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT25x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT25x");
//        RewriteRuleSubtreeStream stream_register_list = new RewriteRuleSubtreeStream(this.adaptor, "rule register_list");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT25x409 = (Token)this.match(this.input, 72, FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x5161);
//                stream_INSTRUCTION_FORMAT25x.add(INSTRUCTION_FORMAT25x409);
//                REGISTER410 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format25x5163);
//                stream_REGISTER.add(REGISTER410);
//                COMMA411 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format25x5165);
//                stream_COMMA.add(COMMA411);
//                OPEN_BRACE412 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format25x5167);
//                stream_OPEN_BRACE.add(OPEN_BRACE412);
//                this.pushFollow(FOLLOW_register_list_in_insn_format25x5169);
//                register_list413 = this.register_list();
//                --this.state._fsp;
//                stream_register_list.add(register_list413.getTree());
//                CLOSE_BRACE414 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format25x5171);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE414);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(159, retval.start, "I_STATEMENT_FORMAT25x"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT25x.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_register_list.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var25) {
//                this.reportError(var25);
//                this.recover(this.input, var25);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var25);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format30t_return insn_format30t() throws RecognitionException {
//        smaliParser.insn_format30t_return retval = new smaliParser.insn_format30t_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT30t415 = null;
//        ParserRuleReturnScope label_ref416 = null;
//        CommonTree INSTRUCTION_FORMAT30t415_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT30t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT30t");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT30t415 = (Token)this.match(this.input, 73, FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5203);
//                stream_INSTRUCTION_FORMAT30t.add(INSTRUCTION_FORMAT30t415);
//                this.pushFollow(FOLLOW_label_ref_in_insn_format30t5205);
//                label_ref416 = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(label_ref416.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(160, retval.start, "I_STATEMENT_FORMAT30t"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT30t.nextNode());
//                this.adaptor.addChild(root_1, stream_label_ref.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var13) {
//                this.reportError(var13);
//                this.recover(this.input, var13);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format31c_return insn_format31c() throws RecognitionException {
//        smaliParser.insn_format31c_return retval = new smaliParser.insn_format31c_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT31c417 = null;
//        Token REGISTER418 = null;
//        Token COMMA419 = null;
//        Token STRING_LITERAL420 = null;
//        CommonTree INSTRUCTION_FORMAT31c417_tree = null;
//        CommonTree REGISTER418_tree = null;
//        CommonTree COMMA419_tree = null;
//        CommonTree STRING_LITERAL420_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31c = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT31c");
//        RewriteRuleTokenStream stream_STRING_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token STRING_LITERAL");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT31c417 = (Token)this.match(this.input, 74, FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5235);
//                stream_INSTRUCTION_FORMAT31c.add(INSTRUCTION_FORMAT31c417);
//                REGISTER418 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format31c5237);
//                stream_REGISTER.add(REGISTER418);
//                COMMA419 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format31c5239);
//                stream_COMMA.add(COMMA419);
//                STRING_LITERAL420 = (Token)this.match(this.input, 198, FOLLOW_STRING_LITERAL_in_insn_format31c5241);
//                stream_STRING_LITERAL.add(STRING_LITERAL420);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(161, retval.start, "I_STATEMENT_FORMAT31c"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT31c.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var20) {
//                this.reportError(var20);
//                this.recover(this.input, var20);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format31i_return insn_format31i() throws RecognitionException {
//        smaliParser.insn_format31i_return retval = new smaliParser.insn_format31i_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token REGISTER422 = null;
//        Token COMMA423 = null;
//        ParserRuleReturnScope instruction_format31i421 = null;
//        ParserRuleReturnScope fixed_32bit_literal424 = null;
//        CommonTree REGISTER422_tree = null;
//        CommonTree COMMA423_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleSubtreeStream stream_fixed_32bit_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_32bit_literal");
//        RewriteRuleSubtreeStream stream_instruction_format31i = new RewriteRuleSubtreeStream(this.adaptor, "rule instruction_format31i");
//
//        try {
//            try {
//                this.pushFollow(FOLLOW_instruction_format31i_in_insn_format31i5272);
//                instruction_format31i421 = this.instruction_format31i();
//                --this.state._fsp;
//                stream_instruction_format31i.add(instruction_format31i421.getTree());
//                REGISTER422 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format31i5274);
//                stream_REGISTER.add(REGISTER422);
//                COMMA423 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format31i5276);
//                stream_COMMA.add(COMMA423);
//                this.pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format31i5278);
//                fixed_32bit_literal424 = this.fixed_32bit_literal();
//                --this.state._fsp;
//                stream_fixed_32bit_literal.add(fixed_32bit_literal424.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(162, retval.start, "I_STATEMENT_FORMAT31i"), root_1);
//                this.adaptor.addChild(root_1, stream_instruction_format31i.nextTree());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format31t_return insn_format31t() throws RecognitionException {
//        smaliParser.insn_format31t_return retval = new smaliParser.insn_format31t_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT31t425 = null;
//        Token REGISTER426 = null;
//        Token COMMA427 = null;
//        ParserRuleReturnScope label_ref428 = null;
//        CommonTree INSTRUCTION_FORMAT31t425_tree = null;
//        CommonTree REGISTER426_tree = null;
//        CommonTree COMMA427_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31t = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT31t");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT31t425 = (Token)this.match(this.input, 77, FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5310);
//                stream_INSTRUCTION_FORMAT31t.add(INSTRUCTION_FORMAT31t425);
//                REGISTER426 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format31t5312);
//                stream_REGISTER.add(REGISTER426);
//                COMMA427 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format31t5314);
//                stream_COMMA.add(COMMA427);
//                this.pushFollow(FOLLOW_label_ref_in_insn_format31t5316);
//                label_ref428 = this.label_ref();
//                --this.state._fsp;
//                stream_label_ref.add(label_ref428.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(163, retval.start, "I_STATEMENT_FORMAT31t"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT31t.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_label_ref.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format32x_return insn_format32x() throws RecognitionException {
//        smaliParser.insn_format32x_return retval = new smaliParser.insn_format32x_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT32x429 = null;
//        Token REGISTER430 = null;
//        Token COMMA431 = null;
//        Token REGISTER432 = null;
//        CommonTree INSTRUCTION_FORMAT32x429_tree = null;
//        CommonTree REGISTER430_tree = null;
//        CommonTree COMMA431_tree = null;
//        CommonTree REGISTER432_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT32x = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT32x");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT32x429 = (Token)this.match(this.input, 78, FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5348);
//                stream_INSTRUCTION_FORMAT32x.add(INSTRUCTION_FORMAT32x429);
//                REGISTER430 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format32x5350);
//                stream_REGISTER.add(REGISTER430);
//                COMMA431 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format32x5352);
//                stream_COMMA.add(COMMA431);
//                REGISTER432 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format32x5354);
//                stream_REGISTER.add(REGISTER432);
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(164, retval.start, "I_STATEMENT_FORMAT32x"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT32x.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format35c_method_return insn_format35c_method() throws RecognitionException {
//        smaliParser.insn_format35c_method_return retval = new smaliParser.insn_format35c_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT35c_METHOD433 = null;
//        Token OPEN_BRACE434 = null;
//        Token CLOSE_BRACE436 = null;
//        Token COMMA437 = null;
//        ParserRuleReturnScope register_list435 = null;
//        ParserRuleReturnScope method_reference438 = null;
//        CommonTree INSTRUCTION_FORMAT35c_METHOD433_tree = null;
//        CommonTree OPEN_BRACE434_tree = null;
//        CommonTree CLOSE_BRACE436_tree = null;
//        CommonTree COMMA437_tree = null;
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35c_METHOD");
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleSubtreeStream stream_method_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule method_reference");
//        RewriteRuleSubtreeStream stream_register_list = new RewriteRuleSubtreeStream(this.adaptor, "rule register_list");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT35c_METHOD433 = (Token)this.match(this.input, 79, FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method5386);
//                stream_INSTRUCTION_FORMAT35c_METHOD.add(INSTRUCTION_FORMAT35c_METHOD433);
//                OPEN_BRACE434 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format35c_method5388);
//                stream_OPEN_BRACE.add(OPEN_BRACE434);
//                this.pushFollow(FOLLOW_register_list_in_insn_format35c_method5390);
//                register_list435 = this.register_list();
//                --this.state._fsp;
//                stream_register_list.add(register_list435.getTree());
//                CLOSE_BRACE436 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format35c_method5392);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE436);
//                COMMA437 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format35c_method5394);
//                stream_COMMA.add(COMMA437);
//                this.pushFollow(FOLLOW_method_reference_in_insn_format35c_method5396);
//                method_reference438 = this.method_reference();
//                --this.state._fsp;
//                stream_method_reference.add(method_reference438.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(165, retval.start, "I_STATEMENT_FORMAT35c_METHOD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT35c_METHOD.nextNode());
//                this.adaptor.addChild(root_1, stream_register_list.nextTree());
//                this.adaptor.addChild(root_1, stream_method_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var24) {
//                this.reportError(var24);
//                this.recover(this.input, var24);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var24);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format35c_type_return insn_format35c_type() throws RecognitionException {
//        smaliParser.insn_format35c_type_return retval = new smaliParser.insn_format35c_type_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT35c_TYPE439 = null;
//        Token OPEN_BRACE440 = null;
//        Token CLOSE_BRACE442 = null;
//        Token COMMA443 = null;
//        ParserRuleReturnScope register_list441 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor444 = null;
//        CommonTree INSTRUCTION_FORMAT35c_TYPE439_tree = null;
//        CommonTree OPEN_BRACE440_tree = null;
//        CommonTree CLOSE_BRACE442_tree = null;
//        CommonTree COMMA443_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT35c_TYPE");
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleSubtreeStream stream_register_list = new RewriteRuleSubtreeStream(this.adaptor, "rule register_list");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT35c_TYPE439 = (Token)this.match(this.input, 81, FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5428);
//                stream_INSTRUCTION_FORMAT35c_TYPE.add(INSTRUCTION_FORMAT35c_TYPE439);
//                OPEN_BRACE440 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format35c_type5430);
//                stream_OPEN_BRACE.add(OPEN_BRACE440);
//                this.pushFollow(FOLLOW_register_list_in_insn_format35c_type5432);
//                register_list441 = this.register_list();
//                --this.state._fsp;
//                stream_register_list.add(register_list441.getTree());
//                CLOSE_BRACE442 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format35c_type5434);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE442);
//                COMMA443 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format35c_type5436);
//                stream_COMMA.add(COMMA443);
//                this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5438);
//                nonvoid_type_descriptor444 = this.nonvoid_type_descriptor();
//                --this.state._fsp;
//                stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor444.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(166, retval.start, "I_STATEMENT_FORMAT35c_TYPE"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT35c_TYPE.nextNode());
//                this.adaptor.addChild(root_1, stream_register_list.nextTree());
//                this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var24) {
//                this.reportError(var24);
//                this.recover(this.input, var24);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var24);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format35c_method_odex_return insn_format35c_method_odex() throws RecognitionException {
//        smaliParser.insn_format35c_method_odex_return retval = new smaliParser.insn_format35c_method_odex_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT35c_METHOD_ODEX445 = null;
//        Token OPEN_BRACE446 = null;
//        Token CLOSE_BRACE448 = null;
//        Token COMMA449 = null;
//        ParserRuleReturnScope register_list447 = null;
//        ParserRuleReturnScope method_reference450 = null;
//        CommonTree INSTRUCTION_FORMAT35c_METHOD_ODEX445_tree = null;
//        CommonTree OPEN_BRACE446_tree = null;
//        CommonTree CLOSE_BRACE448_tree = null;
//        CommonTree COMMA449_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT35c_METHOD_ODEX445 = (Token)this.match(this.input, 80, FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5470);
//                INSTRUCTION_FORMAT35c_METHOD_ODEX445_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT35c_METHOD_ODEX445);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT35c_METHOD_ODEX445_tree);
//                OPEN_BRACE446 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5472);
//                OPEN_BRACE446_tree = (CommonTree)this.adaptor.create(OPEN_BRACE446);
//                this.adaptor.addChild(root_0, OPEN_BRACE446_tree);
//                this.pushFollow(FOLLOW_register_list_in_insn_format35c_method_odex5474);
//                register_list447 = this.register_list();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, register_list447.getTree());
//                CLOSE_BRACE448 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5476);
//                CLOSE_BRACE448_tree = (CommonTree)this.adaptor.create(CLOSE_BRACE448);
//                this.adaptor.addChild(root_0, CLOSE_BRACE448_tree);
//                COMMA449 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format35c_method_odex5478);
//                COMMA449_tree = (CommonTree)this.adaptor.create(COMMA449);
//                this.adaptor.addChild(root_0, COMMA449_tree);
//                this.pushFollow(FOLLOW_method_reference_in_insn_format35c_method_odex5480);
//                method_reference450 = this.method_reference();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, method_reference450.getTree());
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT35c_METHOD_ODEX445 != null ? INSTRUCTION_FORMAT35c_METHOD_ODEX445.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var17) {
//                this.reportError(var17);
//                this.recover(this.input, var17);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var17);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format35mi_method_return insn_format35mi_method() throws RecognitionException {
//        smaliParser.insn_format35mi_method_return retval = new smaliParser.insn_format35mi_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT35mi_METHOD451 = null;
//        Token OPEN_BRACE452 = null;
//        Token CLOSE_BRACE454 = null;
//        Token COMMA455 = null;
//        Token INLINE_INDEX456 = null;
//        ParserRuleReturnScope register_list453 = null;
//        CommonTree INSTRUCTION_FORMAT35mi_METHOD451_tree = null;
//        CommonTree OPEN_BRACE452_tree = null;
//        CommonTree CLOSE_BRACE454_tree = null;
//        CommonTree COMMA455_tree = null;
//        CommonTree INLINE_INDEX456_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT35mi_METHOD451 = (Token)this.match(this.input, 82, FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5501);
//                INSTRUCTION_FORMAT35mi_METHOD451_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT35mi_METHOD451);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT35mi_METHOD451_tree);
//                OPEN_BRACE452 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format35mi_method5503);
//                OPEN_BRACE452_tree = (CommonTree)this.adaptor.create(OPEN_BRACE452);
//                this.adaptor.addChild(root_0, OPEN_BRACE452_tree);
//                this.pushFollow(FOLLOW_register_list_in_insn_format35mi_method5505);
//                register_list453 = this.register_list();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, register_list453.getTree());
//                CLOSE_BRACE454 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5507);
//                CLOSE_BRACE454_tree = (CommonTree)this.adaptor.create(CLOSE_BRACE454);
//                this.adaptor.addChild(root_0, CLOSE_BRACE454_tree);
//                COMMA455 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format35mi_method5509);
//                COMMA455_tree = (CommonTree)this.adaptor.create(COMMA455);
//                this.adaptor.addChild(root_0, COMMA455_tree);
//                INLINE_INDEX456 = (Token)this.match(this.input, 41, FOLLOW_INLINE_INDEX_in_insn_format35mi_method5511);
//                INLINE_INDEX456_tree = (CommonTree)this.adaptor.create(INLINE_INDEX456);
//                this.adaptor.addChild(root_0, INLINE_INDEX456_tree);
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT35mi_METHOD451 != null ? INSTRUCTION_FORMAT35mi_METHOD451.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format35ms_method_return insn_format35ms_method() throws RecognitionException {
//        smaliParser.insn_format35ms_method_return retval = new smaliParser.insn_format35ms_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT35ms_METHOD457 = null;
//        Token OPEN_BRACE458 = null;
//        Token CLOSE_BRACE460 = null;
//        Token COMMA461 = null;
//        Token VTABLE_INDEX462 = null;
//        ParserRuleReturnScope register_list459 = null;
//        CommonTree INSTRUCTION_FORMAT35ms_METHOD457_tree = null;
//        CommonTree OPEN_BRACE458_tree = null;
//        CommonTree CLOSE_BRACE460_tree = null;
//        CommonTree COMMA461_tree = null;
//        CommonTree VTABLE_INDEX462_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT35ms_METHOD457 = (Token)this.match(this.input, 83, FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5532);
//                INSTRUCTION_FORMAT35ms_METHOD457_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT35ms_METHOD457);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT35ms_METHOD457_tree);
//                OPEN_BRACE458 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format35ms_method5534);
//                OPEN_BRACE458_tree = (CommonTree)this.adaptor.create(OPEN_BRACE458);
//                this.adaptor.addChild(root_0, OPEN_BRACE458_tree);
//                this.pushFollow(FOLLOW_register_list_in_insn_format35ms_method5536);
//                register_list459 = this.register_list();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, register_list459.getTree());
//                CLOSE_BRACE460 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5538);
//                CLOSE_BRACE460_tree = (CommonTree)this.adaptor.create(CLOSE_BRACE460);
//                this.adaptor.addChild(root_0, CLOSE_BRACE460_tree);
//                COMMA461 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format35ms_method5540);
//                COMMA461_tree = (CommonTree)this.adaptor.create(COMMA461);
//                this.adaptor.addChild(root_0, COMMA461_tree);
//                VTABLE_INDEX462 = (Token)this.match(this.input, 203, FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5542);
//                VTABLE_INDEX462_tree = (CommonTree)this.adaptor.create(VTABLE_INDEX462);
//                this.adaptor.addChild(root_0, VTABLE_INDEX462_tree);
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT35ms_METHOD457 != null ? INSTRUCTION_FORMAT35ms_METHOD457.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format3rc_method_return insn_format3rc_method() throws RecognitionException {
//        smaliParser.insn_format3rc_method_return retval = new smaliParser.insn_format3rc_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT3rc_METHOD463 = null;
//        Token OPEN_BRACE464 = null;
//        Token CLOSE_BRACE466 = null;
//        Token COMMA467 = null;
//        ParserRuleReturnScope register_range465 = null;
//        ParserRuleReturnScope method_reference468 = null;
//        CommonTree INSTRUCTION_FORMAT3rc_METHOD463_tree = null;
//        CommonTree OPEN_BRACE464_tree = null;
//        CommonTree CLOSE_BRACE466_tree = null;
//        CommonTree COMMA467_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_METHOD = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT3rc_METHOD");
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleSubtreeStream stream_method_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule method_reference");
//        RewriteRuleSubtreeStream stream_register_range = new RewriteRuleSubtreeStream(this.adaptor, "rule register_range");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT3rc_METHOD463 = (Token)this.match(this.input, 84, FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5563);
//                stream_INSTRUCTION_FORMAT3rc_METHOD.add(INSTRUCTION_FORMAT3rc_METHOD463);
//                OPEN_BRACE464 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format3rc_method5565);
//                stream_OPEN_BRACE.add(OPEN_BRACE464);
//                this.pushFollow(FOLLOW_register_range_in_insn_format3rc_method5567);
//                register_range465 = this.register_range();
//                --this.state._fsp;
//                stream_register_range.add(register_range465.getTree());
//                CLOSE_BRACE466 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5569);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE466);
//                COMMA467 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format3rc_method5571);
//                stream_COMMA.add(COMMA467);
//                this.pushFollow(FOLLOW_method_reference_in_insn_format3rc_method5573);
//                method_reference468 = this.method_reference();
//                --this.state._fsp;
//                stream_method_reference.add(method_reference468.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(167, retval.start, "I_STATEMENT_FORMAT3rc_METHOD"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_METHOD.nextNode());
//                this.adaptor.addChild(root_1, stream_register_range.nextTree());
//                this.adaptor.addChild(root_1, stream_method_reference.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var24) {
//                this.reportError(var24);
//                this.recover(this.input, var24);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var24);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format3rc_method_odex_return insn_format3rc_method_odex() throws RecognitionException {
//        smaliParser.insn_format3rc_method_odex_return retval = new smaliParser.insn_format3rc_method_odex_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT3rc_METHOD_ODEX469 = null;
//        Token OPEN_BRACE470 = null;
//        Token CLOSE_BRACE472 = null;
//        Token COMMA473 = null;
//        ParserRuleReturnScope register_list471 = null;
//        ParserRuleReturnScope method_reference474 = null;
//        CommonTree INSTRUCTION_FORMAT3rc_METHOD_ODEX469_tree = null;
//        CommonTree OPEN_BRACE470_tree = null;
//        CommonTree CLOSE_BRACE472_tree = null;
//        CommonTree COMMA473_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT3rc_METHOD_ODEX469 = (Token)this.match(this.input, 85, FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5605);
//                INSTRUCTION_FORMAT3rc_METHOD_ODEX469_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT3rc_METHOD_ODEX469);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT3rc_METHOD_ODEX469_tree);
//                OPEN_BRACE470 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5607);
//                OPEN_BRACE470_tree = (CommonTree)this.adaptor.create(OPEN_BRACE470);
//                this.adaptor.addChild(root_0, OPEN_BRACE470_tree);
//                this.pushFollow(FOLLOW_register_list_in_insn_format3rc_method_odex5609);
//                register_list471 = this.register_list();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, register_list471.getTree());
//                CLOSE_BRACE472 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5611);
//                CLOSE_BRACE472_tree = (CommonTree)this.adaptor.create(CLOSE_BRACE472);
//                this.adaptor.addChild(root_0, CLOSE_BRACE472_tree);
//                COMMA473 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format3rc_method_odex5613);
//                COMMA473_tree = (CommonTree)this.adaptor.create(COMMA473);
//                this.adaptor.addChild(root_0, COMMA473_tree);
//                this.pushFollow(FOLLOW_method_reference_in_insn_format3rc_method_odex5615);
//                method_reference474 = this.method_reference();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, method_reference474.getTree());
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT3rc_METHOD_ODEX469 != null ? INSTRUCTION_FORMAT3rc_METHOD_ODEX469.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var17) {
//                this.reportError(var17);
//                this.recover(this.input, var17);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var17);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format3rc_type_return insn_format3rc_type() throws RecognitionException {
//        smaliParser.insn_format3rc_type_return retval = new smaliParser.insn_format3rc_type_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT3rc_TYPE475 = null;
//        Token OPEN_BRACE476 = null;
//        Token CLOSE_BRACE478 = null;
//        Token COMMA479 = null;
//        ParserRuleReturnScope register_range477 = null;
//        ParserRuleReturnScope nonvoid_type_descriptor480 = null;
//        CommonTree INSTRUCTION_FORMAT3rc_TYPE475_tree = null;
//        CommonTree OPEN_BRACE476_tree = null;
//        CommonTree CLOSE_BRACE478_tree = null;
//        CommonTree COMMA479_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_OPEN_BRACE = new RewriteRuleTokenStream(this.adaptor, "token OPEN_BRACE");
//        RewriteRuleTokenStream stream_CLOSE_BRACE = new RewriteRuleTokenStream(this.adaptor, "token CLOSE_BRACE");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_TYPE = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT3rc_TYPE");
//        RewriteRuleSubtreeStream stream_nonvoid_type_descriptor = new RewriteRuleSubtreeStream(this.adaptor, "rule nonvoid_type_descriptor");
//        RewriteRuleSubtreeStream stream_register_range = new RewriteRuleSubtreeStream(this.adaptor, "rule register_range");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT3rc_TYPE475 = (Token)this.match(this.input, 86, FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5636);
//                stream_INSTRUCTION_FORMAT3rc_TYPE.add(INSTRUCTION_FORMAT3rc_TYPE475);
//                OPEN_BRACE476 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format3rc_type5638);
//                stream_OPEN_BRACE.add(OPEN_BRACE476);
//                this.pushFollow(FOLLOW_register_range_in_insn_format3rc_type5640);
//                register_range477 = this.register_range();
//                --this.state._fsp;
//                stream_register_range.add(register_range477.getTree());
//                CLOSE_BRACE478 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5642);
//                stream_CLOSE_BRACE.add(CLOSE_BRACE478);
//                COMMA479 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format3rc_type5644);
//                stream_COMMA.add(COMMA479);
//                this.pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5646);
//                nonvoid_type_descriptor480 = this.nonvoid_type_descriptor();
//                --this.state._fsp;
//                stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor480.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(168, retval.start, "I_STATEMENT_FORMAT3rc_TYPE"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_TYPE.nextNode());
//                this.adaptor.addChild(root_1, stream_register_range.nextTree());
//                this.adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var24) {
//                this.reportError(var24);
//                this.recover(this.input, var24);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var24);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format3rmi_method_return insn_format3rmi_method() throws RecognitionException {
//        smaliParser.insn_format3rmi_method_return retval = new smaliParser.insn_format3rmi_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT3rmi_METHOD481 = null;
//        Token OPEN_BRACE482 = null;
//        Token CLOSE_BRACE484 = null;
//        Token COMMA485 = null;
//        Token INLINE_INDEX486 = null;
//        ParserRuleReturnScope register_range483 = null;
//        CommonTree INSTRUCTION_FORMAT3rmi_METHOD481_tree = null;
//        CommonTree OPEN_BRACE482_tree = null;
//        CommonTree CLOSE_BRACE484_tree = null;
//        CommonTree COMMA485_tree = null;
//        CommonTree INLINE_INDEX486_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT3rmi_METHOD481 = (Token)this.match(this.input, 87, FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method5678);
//                INSTRUCTION_FORMAT3rmi_METHOD481_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT3rmi_METHOD481);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT3rmi_METHOD481_tree);
//                OPEN_BRACE482 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format3rmi_method5680);
//                OPEN_BRACE482_tree = (CommonTree)this.adaptor.create(OPEN_BRACE482);
//                this.adaptor.addChild(root_0, OPEN_BRACE482_tree);
//                this.pushFollow(FOLLOW_register_range_in_insn_format3rmi_method5682);
//                register_range483 = this.register_range();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, register_range483.getTree());
//                CLOSE_BRACE484 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method5684);
//                CLOSE_BRACE484_tree = (CommonTree)this.adaptor.create(CLOSE_BRACE484);
//                this.adaptor.addChild(root_0, CLOSE_BRACE484_tree);
//                COMMA485 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format3rmi_method5686);
//                COMMA485_tree = (CommonTree)this.adaptor.create(COMMA485);
//                this.adaptor.addChild(root_0, COMMA485_tree);
//                INLINE_INDEX486 = (Token)this.match(this.input, 41, FOLLOW_INLINE_INDEX_in_insn_format3rmi_method5688);
//                INLINE_INDEX486_tree = (CommonTree)this.adaptor.create(INLINE_INDEX486);
//                this.adaptor.addChild(root_0, INLINE_INDEX486_tree);
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT3rmi_METHOD481 != null ? INSTRUCTION_FORMAT3rmi_METHOD481.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format3rms_method_return insn_format3rms_method() throws RecognitionException {
//        smaliParser.insn_format3rms_method_return retval = new smaliParser.insn_format3rms_method_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT3rms_METHOD487 = null;
//        Token OPEN_BRACE488 = null;
//        Token CLOSE_BRACE490 = null;
//        Token COMMA491 = null;
//        Token VTABLE_INDEX492 = null;
//        ParserRuleReturnScope register_range489 = null;
//        CommonTree INSTRUCTION_FORMAT3rms_METHOD487_tree = null;
//        CommonTree OPEN_BRACE488_tree = null;
//        CommonTree CLOSE_BRACE490_tree = null;
//        CommonTree COMMA491_tree = null;
//        CommonTree VTABLE_INDEX492_tree = null;
//
//        try {
//            try {
//                root_0 = (CommonTree)this.adaptor.nil();
//                INSTRUCTION_FORMAT3rms_METHOD487 = (Token)this.match(this.input, 88, FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method5709);
//                INSTRUCTION_FORMAT3rms_METHOD487_tree = (CommonTree)this.adaptor.create(INSTRUCTION_FORMAT3rms_METHOD487);
//                this.adaptor.addChild(root_0, INSTRUCTION_FORMAT3rms_METHOD487_tree);
//                OPEN_BRACE488 = (Token)this.match(this.input, 183, FOLLOW_OPEN_BRACE_in_insn_format3rms_method5711);
//                OPEN_BRACE488_tree = (CommonTree)this.adaptor.create(OPEN_BRACE488);
//                this.adaptor.addChild(root_0, OPEN_BRACE488_tree);
//                this.pushFollow(FOLLOW_register_range_in_insn_format3rms_method5713);
//                register_range489 = this.register_range();
//                --this.state._fsp;
//                this.adaptor.addChild(root_0, register_range489.getTree());
//                CLOSE_BRACE490 = (Token)this.match(this.input, 17, FOLLOW_CLOSE_BRACE_in_insn_format3rms_method5715);
//                CLOSE_BRACE490_tree = (CommonTree)this.adaptor.create(CLOSE_BRACE490);
//                this.adaptor.addChild(root_0, CLOSE_BRACE490_tree);
//                COMMA491 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format3rms_method5717);
//                COMMA491_tree = (CommonTree)this.adaptor.create(COMMA491);
//                this.adaptor.addChild(root_0, COMMA491_tree);
//                VTABLE_INDEX492 = (Token)this.match(this.input, 203, FOLLOW_VTABLE_INDEX_in_insn_format3rms_method5719);
//                VTABLE_INDEX492_tree = (CommonTree)this.adaptor.create(VTABLE_INDEX492);
//                this.adaptor.addChild(root_0, VTABLE_INDEX492_tree);
//                this.throwOdexedInstructionException(this.input, INSTRUCTION_FORMAT3rms_METHOD487 != null ? INSTRUCTION_FORMAT3rms_METHOD487.getText() : null);
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var18) {
//                this.reportError(var18);
//                this.recover(this.input, var18);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_format51l_return insn_format51l() throws RecognitionException {
//        smaliParser.insn_format51l_return retval = new smaliParser.insn_format51l_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token INSTRUCTION_FORMAT51l493 = null;
//        Token REGISTER494 = null;
//        Token COMMA495 = null;
//        ParserRuleReturnScope fixed_literal496 = null;
//        CommonTree INSTRUCTION_FORMAT51l493_tree = null;
//        CommonTree REGISTER494_tree = null;
//        CommonTree COMMA495_tree = null;
//        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
//        RewriteRuleTokenStream stream_REGISTER = new RewriteRuleTokenStream(this.adaptor, "token REGISTER");
//        RewriteRuleTokenStream stream_INSTRUCTION_FORMAT51l = new RewriteRuleTokenStream(this.adaptor, "token INSTRUCTION_FORMAT51l");
//        RewriteRuleSubtreeStream stream_fixed_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_literal");
//
//        try {
//            try {
//                INSTRUCTION_FORMAT51l493 = (Token)this.match(this.input, 89, FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l5740);
//                stream_INSTRUCTION_FORMAT51l.add(INSTRUCTION_FORMAT51l493);
//                REGISTER494 = (Token)this.match(this.input, 191, FOLLOW_REGISTER_in_insn_format51l5742);
//                stream_REGISTER.add(REGISTER494);
//                COMMA495 = (Token)this.match(this.input, 20, FOLLOW_COMMA_in_insn_format51l5744);
//                stream_COMMA.add(COMMA495);
//                this.pushFollow(FOLLOW_fixed_literal_in_insn_format51l5746);
//                fixed_literal496 = this.fixed_literal();
//                --this.state._fsp;
//                stream_fixed_literal.add(fixed_literal496.getTree());
//                retval.tree = root_0;
//                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                root_0 = (CommonTree)this.adaptor.nil();
//                CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(169, retval.start, "I_STATEMENT_FORMAT51l"), root_1);
//                this.adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT51l.nextNode());
//                this.adaptor.addChild(root_1, stream_REGISTER.nextNode());
//                this.adaptor.addChild(root_1, stream_fixed_literal.nextTree());
//                this.adaptor.addChild(root_0, root_1);
//                retval.tree = root_0;
//                retval.stop = this.input.LT(-1);
//                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//            } catch (RecognitionException var19) {
//                this.reportError(var19);
//                this.recover(this.input, var19);
//                retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            }
//
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_array_data_directive_return insn_array_data_directive() throws RecognitionException {
//        smaliParser.insn_array_data_directive_return retval = new smaliParser.insn_array_data_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token ARRAY_DATA_DIRECTIVE497 = null;
//        Token END_ARRAY_DATA_DIRECTIVE500 = null;
//        ParserRuleReturnScope parsed_integer_literal498 = null;
//        ParserRuleReturnScope fixed_literal499 = null;
//        CommonTree ARRAY_DATA_DIRECTIVE497_tree = null;
//        CommonTree END_ARRAY_DATA_DIRECTIVE500_tree = null;
//        RewriteRuleTokenStream stream_END_ARRAY_DATA_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_ARRAY_DATA_DIRECTIVE");
//        RewriteRuleTokenStream stream_ARRAY_DATA_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token ARRAY_DATA_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_parsed_integer_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule parsed_integer_literal");
//        RewriteRuleSubtreeStream stream_fixed_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_literal");
//
//        try {
//            ARRAY_DATA_DIRECTIVE497 = (Token)this.match(this.input, 7, FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5773);
//            stream_ARRAY_DATA_DIRECTIVE.add(ARRAY_DATA_DIRECTIVE497);
//            this.pushFollow(FOLLOW_parsed_integer_literal_in_insn_array_data_directive5779);
//            parsed_integer_literal498 = this.parsed_integer_literal();
//            --this.state._fsp;
//            stream_parsed_integer_literal.add(parsed_integer_literal498.getTree());
//            int elementWidth = parsed_integer_literal498 != null ? ((smaliParser.parsed_integer_literal_return)parsed_integer_literal498).value : 0;
//            if (elementWidth != 4 && elementWidth != 8 && elementWidth != 1 && elementWidth != 2) {
//                throw new SemanticException(this.input, retval.start, "Invalid element width: %d. Must be 1, 2, 4 or 8", new Object[]{elementWidth});
//            } else {
//                while(true) {
//                    int alt52 = 2;
//                    int LA52_0 = this.input.LA(1);
//                    if (LA52_0 >= 10 && LA52_0 <= 11 || LA52_0 == 14 || LA52_0 >= 22 && LA52_0 <= 23 || LA52_0 >= 38 && LA52_0 <= 39 || LA52_0 == 178 || LA52_0 == 181 || LA52_0 == 188 || LA52_0 == 194) {
//                        alt52 = 1;
//                    }
//
//                    switch(alt52) {
//                        case 1:
//                            this.pushFollow(FOLLOW_fixed_literal_in_insn_array_data_directive5791);
//                            fixed_literal499 = this.fixed_literal();
//                            --this.state._fsp;
//                            stream_fixed_literal.add(fixed_literal499.getTree());
//                            break;
//                        default:
//                            END_ARRAY_DATA_DIRECTIVE500 = (Token)this.match(this.input, 25, FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5794);
//                            stream_END_ARRAY_DATA_DIRECTIVE.add(END_ARRAY_DATA_DIRECTIVE500);
//                            retval.tree = root_0;
//                            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                            root_0 = (CommonTree)this.adaptor.nil();
//                            CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                            root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(134, retval.start, "I_STATEMENT_ARRAY_DATA"), root_1);
//                            CommonTree root_2 = (CommonTree)this.adaptor.nil();
//                            root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(97, "I_ARRAY_ELEMENT_SIZE"), root_2);
//                            this.adaptor.addChild(root_2, stream_parsed_integer_literal.nextTree());
//                            this.adaptor.addChild(root_1, root_2);
//                            root_2 = (CommonTree)this.adaptor.nil();
//                            root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(96, "I_ARRAY_ELEMENTS"), root_2);
//
//                            while(stream_fixed_literal.hasNext()) {
//                                this.adaptor.addChild(root_2, stream_fixed_literal.nextTree());
//                            }
//
//                            stream_fixed_literal.reset();
//                            this.adaptor.addChild(root_1, root_2);
//                            this.adaptor.addChild(root_0, root_1);
//                            retval.tree = root_0;
//                            retval.stop = this.input.LT(-1);
//                            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                            return retval;
//                    }
//                }
//            }
//        } catch (RecognitionException var20) {
//            this.reportError(var20);
//            this.recover(this.input, var20);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_packed_switch_directive_return insn_packed_switch_directive() throws RecognitionException {
//        smaliParser.insn_packed_switch_directive_return retval = new smaliParser.insn_packed_switch_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token PACKED_SWITCH_DIRECTIVE501 = null;
//        Token END_PACKED_SWITCH_DIRECTIVE504 = null;
//        ParserRuleReturnScope fixed_32bit_literal502 = null;
//        ParserRuleReturnScope label_ref503 = null;
//        CommonTree PACKED_SWITCH_DIRECTIVE501_tree = null;
//        CommonTree END_PACKED_SWITCH_DIRECTIVE504_tree = null;
//        RewriteRuleTokenStream stream_END_PACKED_SWITCH_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_PACKED_SWITCH_DIRECTIVE");
//        RewriteRuleTokenStream stream_PACKED_SWITCH_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token PACKED_SWITCH_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_fixed_32bit_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_32bit_literal");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            PACKED_SWITCH_DIRECTIVE501 = (Token)this.match(this.input, 185, FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5840);
//            stream_PACKED_SWITCH_DIRECTIVE.add(PACKED_SWITCH_DIRECTIVE501);
//            this.pushFollow(FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive5846);
//            fixed_32bit_literal502 = this.fixed_32bit_literal();
//            --this.state._fsp;
//            stream_fixed_32bit_literal.add(fixed_32bit_literal502.getTree());
//
//            while(true) {
//                int alt53 = 2;
//                int LA53_0 = this.input.LA(1);
//                if (LA53_0 == 19) {
//                    alt53 = 1;
//                }
//
//                switch(alt53) {
//                    case 1:
//                        this.pushFollow(FOLLOW_label_ref_in_insn_packed_switch_directive5852);
//                        label_ref503 = this.label_ref();
//                        --this.state._fsp;
//                        stream_label_ref.add(label_ref503.getTree());
//                        break;
//                    default:
//                        END_PACKED_SWITCH_DIRECTIVE504 = (Token)this.match(this.input, 29, FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5859);
//                        stream_END_PACKED_SWITCH_DIRECTIVE.add(END_PACKED_SWITCH_DIRECTIVE504);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(170, retval.start, "I_STATEMENT_PACKED_SWITCH"), root_1);
//                        CommonTree root_2 = (CommonTree)this.adaptor.nil();
//                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(123, retval.start, "I_PACKED_SWITCH_START_KEY"), root_2);
//                        this.adaptor.addChild(root_2, stream_fixed_32bit_literal.nextTree());
//                        this.adaptor.addChild(root_1, root_2);
//                        root_2 = (CommonTree)this.adaptor.nil();
//                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(122, retval.start, "I_PACKED_SWITCH_ELEMENTS"), root_2);
//
//                        while(stream_label_ref.hasNext()) {
//                            this.adaptor.addChild(root_2, stream_label_ref.nextTree());
//                        }
//
//                        stream_label_ref.reset();
//                        this.adaptor.addChild(root_1, root_2);
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var19) {
//            this.reportError(var19);
//            this.recover(this.input, var19);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    public final smaliParser.insn_sparse_switch_directive_return insn_sparse_switch_directive() throws RecognitionException {
//        smaliParser.insn_sparse_switch_directive_return retval = new smaliParser.insn_sparse_switch_directive_return();
//        retval.start = this.input.LT(1);
//        CommonTree root_0 = null;
//        Token SPARSE_SWITCH_DIRECTIVE505 = null;
//        Token ARROW507 = null;
//        Token END_SPARSE_SWITCH_DIRECTIVE509 = null;
//        ParserRuleReturnScope fixed_32bit_literal506 = null;
//        ParserRuleReturnScope label_ref508 = null;
//        CommonTree SPARSE_SWITCH_DIRECTIVE505_tree = null;
//        CommonTree ARROW507_tree = null;
//        CommonTree END_SPARSE_SWITCH_DIRECTIVE509_tree = null;
//        RewriteRuleTokenStream stream_ARROW = new RewriteRuleTokenStream(this.adaptor, "token ARROW");
//        RewriteRuleTokenStream stream_SPARSE_SWITCH_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token SPARSE_SWITCH_DIRECTIVE");
//        RewriteRuleTokenStream stream_END_SPARSE_SWITCH_DIRECTIVE = new RewriteRuleTokenStream(this.adaptor, "token END_SPARSE_SWITCH_DIRECTIVE");
//        RewriteRuleSubtreeStream stream_fixed_32bit_literal = new RewriteRuleSubtreeStream(this.adaptor, "rule fixed_32bit_literal");
//        RewriteRuleSubtreeStream stream_label_ref = new RewriteRuleSubtreeStream(this.adaptor, "rule label_ref");
//
//        try {
//            SPARSE_SWITCH_DIRECTIVE505 = (Token)this.match(this.input, 197, FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5933);
//            stream_SPARSE_SWITCH_DIRECTIVE.add(SPARSE_SWITCH_DIRECTIVE505);
//
//            while(true) {
//                int alt54 = 2;
//                int LA54_0 = this.input.LA(1);
//                if (LA54_0 >= 10 && LA54_0 <= 11 || LA54_0 == 14 || LA54_0 >= 38 && LA54_0 <= 39 || LA54_0 == 178 || LA54_0 == 181 || LA54_0 == 188 || LA54_0 == 194) {
//                    alt54 = 1;
//                }
//
//                switch(alt54) {
//                    case 1:
//                        this.pushFollow(FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive5940);
//                        fixed_32bit_literal506 = this.fixed_32bit_literal();
//                        --this.state._fsp;
//                        stream_fixed_32bit_literal.add(fixed_32bit_literal506.getTree());
//                        ARROW507 = (Token)this.match(this.input, 9, FOLLOW_ARROW_in_insn_sparse_switch_directive5942);
//                        stream_ARROW.add(ARROW507);
//                        this.pushFollow(FOLLOW_label_ref_in_insn_sparse_switch_directive5944);
//                        label_ref508 = this.label_ref();
//                        --this.state._fsp;
//                        stream_label_ref.add(label_ref508.getTree());
//                        break;
//                    default:
//                        END_SPARSE_SWITCH_DIRECTIVE509 = (Token)this.match(this.input, 31, FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5952);
//                        stream_END_SPARSE_SWITCH_DIRECTIVE.add(END_SPARSE_SWITCH_DIRECTIVE509);
//                        retval.tree = root_0;
//                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.getTree() : null);
//                        root_0 = (CommonTree)this.adaptor.nil();
//                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
//                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(171, retval.start, "I_STATEMENT_SPARSE_SWITCH"), root_1);
//                        CommonTree root_2 = (CommonTree)this.adaptor.nil();
//                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(133, retval.start, "I_SPARSE_SWITCH_ELEMENTS"), root_2);
//
//                        while(stream_fixed_32bit_literal.hasNext() || stream_label_ref.hasNext()) {
//                            this.adaptor.addChild(root_2, stream_fixed_32bit_literal.nextTree());
//                            this.adaptor.addChild(root_2, stream_label_ref.nextTree());
//                        }
//
//                        stream_fixed_32bit_literal.reset();
//                        stream_label_ref.reset();
//                        this.adaptor.addChild(root_1, root_2);
//                        this.adaptor.addChild(root_0, root_1);
//                        retval.tree = root_0;
//                        retval.stop = this.input.LT(-1);
//                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
//                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
//                        return retval;
//                }
//            }
//        } catch (RecognitionException var22) {
//            this.reportError(var22);
//            this.recover(this.input, var22);
//            retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var22);
//            return retval;
//        } finally {
//            ;
//        }
//    }
//
//    static {
//        int numStates = DFA30_transitionS.length;
//        DFA30_transition = new short[numStates][];
//
//        int i;
//        for(i = 0; i < numStates; ++i) {
//            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
//        }
//
//        DFA38_transitionS = new String[]{"\u0001\u0004\u0001\uffff\u0001\u0010\u0001\uffff\u0001\u0002\u0001\uffff\u0001\n\u0004\uffff\u0001\u0001\u0007\uffff\u0001\t\u000f\uffff\u0001\b\u0002\uffff\u0001\u0011\u0001\u0012\u0001\u0013\u0001\uffff\u0001\u0014\u0001\uffff\u0001\u0015\u0002\uffff\u0001\u0016\u0001\u0017\u0001\u001a\u0001\u001b\u0001\u0018\u0001\u0019\u0003\uffff\u0001\u001c\u0001\uffff\u0001\u001d\u0001\u001e\u0001 \u0001\u001f\u0001!\u0001\uffff\u0001\"\u0001#\u0001\uffff\u0001$\u0001%\u0003\uffff\u0001&\u0001'\u0001\uffff\u0001(\u0001)\u0001*\u0001+\u0001,\u0005\uffff\u0001-Y\uffff\u0001.\u0001\uffff\u0001\u0007\u0001\u000b\u0004\uffff\u0001\r\u0001\u0006\u0001\u000e\u0001\uffff\u0001\f\u0003\uffff\u0001\u0003\u0005\uffff\u0001\u0005\u0001\u000f", "\u0001/\u0001\uffff\u0001/\u0001\uffff\u00010\u0002\uffff\u0002/\u0005\uffff\u0001/\u0007\uffff\u0002/\u0005\uffff\u0001/\u0007\uffff0/U\uffff\u0003/\u0007\uffff\u0002/\u0003\uffff\u0001/\u0001\uffff\u0002/\u0002\uffff\u0002/", "\u00011\u00ad\uffff\u00011", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013\u0002\uffff\u0001\r", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "\u00012¤\uffff\u00013", "", "\u0001\u0004\u0001\uffff\u0001\u0010\u0003\uffff\u0001\n\f\uffff\u0001\t\u000f\uffff\u0001\b\u0002\uffff\u0001\u0011\u0001\u0012\u0001\u0013\u0001\uffff\u0001\u0014\u0001\uffff\u0001\u0015\u0002\uffff\u0001\u0016\u0001\u0017\u0001\u001a\u0001\u001b\u0001\u0018\u0001\u0019\u0003\uffff\u0001\u001c\u0001\uffff\u0001\u001d\u0001\u001e\u0001 \u0001\u001f\u0001!\u0001\uffff\u0001\"\u0001#\u0001\uffff\u0001$\u0001%\u0003\uffff\u0001&\u0001'\u0001\uffff\u0001(\u0001)\u0001*\u0001+\u0001,\u0005\uffff\u0001-Y\uffff\u0001.\u0001\uffff\u0001\u0007\u0001\u000b\u0004\uffff\u0001\r\u0001\u0006\u0001\u000e\u0001\uffff\u0001\f\u0003\uffff\u0001\u0003\u0005\uffff\u0001\u0005\u0001\u000f", "\u00010", "", ""};
//        DFA38_eot = DFA.unpackEncodedString("4\uffff");
//        DFA38_eof = DFA.unpackEncodedString("4\uffff");
//        DFA38_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\u0004\u0001\u0005\u0001\u000f,\u0013\u0001\uffff\u0001\u0004\u0001\t\u0002\uffff");
//        DFA38_max = DFA.unpackEncodedStringToUnsignedChars("\u0001Ê\u0001Å\u0001½\n¸\u0001»!¸\u0001\uffff\u0001Ê\u0001\t\u0002\uffff");
//        DFA38_accept = DFA.unpackEncodedString("/\uffff\u0001\u0001\u0002\uffff\u0001\u0002\u0001\u0003");
//        DFA38_special = DFA.unpackEncodedString("4\uffff}>");
//        numStates = DFA38_transitionS.length;
//        DFA38_transition = new short[numStates][];
//
//        for(i = 0; i < numStates; ++i) {
//            DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
//        }
//
//        DFA40_transitionS = new String[]{"\u0001C\u0001\uffff\u0001\u0001\u0004\uffff\u0002\u0001\u0005\uffff\u0001\u0001\u0007\uffff\u0002\u0001\u0001\uffff\u0001\u0001\u0003\uffff\u0001\u0001\u0007\uffff0\u0001U\uffff\u0003\u0001\u0007\uffff\u0002\u0001\u0003\uffff\u0001\u0001\u0001\uffff\u0002\u0001\u0002\uffff\u0002\u0001", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "\u0001\uffff", ""};
//        DFA40_eot = DFA.unpackEncodedString("E\uffff");
//        DFA40_eof = DFA.unpackEncodedString("E\uffff");
//        DFA40_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\u0005B\uffff\u0001\u0000\u0001\uffff");
//        DFA40_max = DFA.unpackEncodedStringToUnsignedChars("\u0001ÅB\uffff\u0001\u0000\u0001\uffff");
//        DFA40_accept = DFA.unpackEncodedString("\u0001\uffff\u0001\u0002B\uffff\u0001\u0001");
//        DFA40_special = DFA.unpackEncodedString("C\uffff\u0001\u0000\u0001\uffff}>");
//        numStates = DFA40_transitionS.length;
//        DFA40_transition = new short[numStates][];
//
//        for(i = 0; i < numStates; ++i) {
//            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
//        }
//
//        FOLLOW_class_spec_in_smali_file1095 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_super_spec_in_smali_file1106 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_implements_spec_in_smali_file1114 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_source_spec_in_smali_file1123 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_method_in_smali_file1131 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_field_in_smali_file1137 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_annotation_in_smali_file1143 = new BitSet(new long[]{1168231170080L, 0L, 4503599627370496L, 272L});
//        FOLLOW_EOF_in_smali_file1154 = new BitSet(new long[]{2L});
//        FOLLOW_CLASS_DIRECTIVE_in_class_spec1241 = new BitSet(new long[]{32784L});
//        FOLLOW_access_list_in_class_spec1243 = new BitSet(new long[]{32768L});
//        FOLLOW_CLASS_DESCRIPTOR_in_class_spec1245 = new BitSet(new long[]{2L});
//        FOLLOW_SUPER_DIRECTIVE_in_super_spec1263 = new BitSet(new long[]{32768L});
//        FOLLOW_CLASS_DESCRIPTOR_in_super_spec1265 = new BitSet(new long[]{2L});
//        FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1284 = new BitSet(new long[]{32768L});
//        FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1286 = new BitSet(new long[]{2L});
//        FOLLOW_SOURCE_DIRECTIVE_in_source_spec1305 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_source_spec1307 = new BitSet(new long[]{2L});
//        FOLLOW_ACCESS_SPEC_in_access_list1326 = new BitSet(new long[]{18L});
//        FOLLOW_FIELD_DIRECTIVE_in_field1357 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_access_list_in_field1359 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_member_name_in_field1361 = new BitSet(new long[]{524288L});
//        FOLLOW_COLON_in_field1363 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_field1365 = new BitSet(new long[]{34426847266L});
//        FOLLOW_EQUAL_in_field1368 = new BitSet(new long[]{-3316517662275613360L, 34582967L, -5121718676227096576L, 1740L});
//        FOLLOW_literal_in_field1370 = new BitSet(new long[]{67108898L});
//        FOLLOW_annotation_in_field1383 = new BitSet(new long[]{67108898L});
//        FOLLOW_END_FIELD_DIRECTIVE_in_field1397 = new BitSet(new long[]{2L});
//        FOLLOW_METHOD_DIRECTIVE_in_method1508 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_access_list_in_method1510 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_member_name_in_method1512 = new BitSet(new long[]{0L, 0L, 72057594037927936L});
//        FOLLOW_method_prototype_in_method1514 = new BitSet(new long[]{-4380463452000L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_statements_and_directives_in_method1516 = new BitSet(new long[]{268435456L});
//        FOLLOW_END_METHOD_DIRECTIVE_in_method1522 = new BitSet(new long[]{2L});
//        FOLLOW_ordered_method_item_in_statements_and_directives1567 = new BitSet(new long[]{-4380731887454L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_registers_directive_in_statements_and_directives1575 = new BitSet(new long[]{-4380731887454L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_catch_directive_in_statements_and_directives1583 = new BitSet(new long[]{-4380731887454L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_catchall_directive_in_statements_and_directives1591 = new BitSet(new long[]{-4380731887454L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_parameter_directive_in_statements_and_directives1599 = new BitSet(new long[]{-4380731887454L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_annotation_in_statements_and_directives1607 = new BitSet(new long[]{-4380731887454L, 67108863L, 5045016745073442816L, 51L});
//        FOLLOW_label_in_ordered_method_item1692 = new BitSet(new long[]{2L});
//        FOLLOW_instruction_in_ordered_method_item1698 = new BitSet(new long[]{2L});
//        FOLLOW_debug_directive_in_ordered_method_item1704 = new BitSet(new long[]{2L});
//        FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1724 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_registers_directive1728 = new BitSet(new long[]{2L});
//        FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1748 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_registers_directive1752 = new BitSet(new long[]{2L});
//        FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id1784 = new BitSet(new long[]{2L, 0L, 576460752303423488L});
//        FOLLOW_SIMPLE_NAME_in_simple_name1797 = new BitSet(new long[]{2L});
//        FOLLOW_ACCESS_SPEC_in_simple_name1803 = new BitSet(new long[]{2L});
//        FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1814 = new BitSet(new long[]{2L});
//        FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1825 = new BitSet(new long[]{2L});
//        FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1836 = new BitSet(new long[]{2L});
//        FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1847 = new BitSet(new long[]{2L});
//        FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1858 = new BitSet(new long[]{2L});
//        FOLLOW_BOOL_LITERAL_in_simple_name1869 = new BitSet(new long[]{2L});
//        FOLLOW_NULL_LITERAL_in_simple_name1880 = new BitSet(new long[]{2L});
//        FOLLOW_REGISTER_in_simple_name1891 = new BitSet(new long[]{2L});
//        FOLLOW_param_list_or_id_in_simple_name1902 = new BitSet(new long[]{2L});
//        FOLLOW_PRIMITIVE_TYPE_in_simple_name1912 = new BitSet(new long[]{2L});
//        FOLLOW_VOID_TYPE_in_simple_name1923 = new BitSet(new long[]{2L});
//        FOLLOW_ANNOTATION_VISIBILITY_in_simple_name1934 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name1945 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name1956 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name1967 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name1978 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name1989 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2000 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2011 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2022 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2033 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_simple_name2044 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_simple_name2055 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2066 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2077 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2088 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2099 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_simple_name2110 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2121 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2132 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2143 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2154 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT25x_in_simple_name2165 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2176 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2187 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2198 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2209 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2220 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2231 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2242 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2253 = new BitSet(new long[]{2L});
//        FOLLOW_simple_name_in_member_name2268 = new BitSet(new long[]{2L});
//        FOLLOW_MEMBER_NAME_in_member_name2274 = new BitSet(new long[]{2L});
//        FOLLOW_OPEN_PAREN_in_method_prototype2289 = new BitSet(new long[]{295168L, 0L, 2882303761517117440L});
//        FOLLOW_param_list_in_method_prototype2291 = new BitSet(new long[]{262144L});
//        FOLLOW_CLOSE_PAREN_in_method_prototype2293 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L, 1024L});
//        FOLLOW_type_descriptor_in_method_prototype2295 = new BitSet(new long[]{2L});
//        FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id_primitive_type2325 = new BitSet(new long[]{2L});
//        FOLLOW_param_list_or_id_primitive_type_in_param_list2340 = new BitSet(new long[]{2L, 0L, 576460752303423488L});
//        FOLLOW_nonvoid_type_descriptor_in_param_list2347 = new BitSet(new long[]{33026L, 0L, 2305843009213693952L});
//        FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor2358 = new BitSet(new long[]{32768L, 0L, 2305843009213693952L});
//        FOLLOW_set_in_array_descriptor2360 = new BitSet(new long[]{2L});
//        FOLLOW_VOID_TYPE_in_type_descriptor2376 = new BitSet(new long[]{2L});
//        FOLLOW_PRIMITIVE_TYPE_in_type_descriptor2382 = new BitSet(new long[]{2L});
//        FOLLOW_CLASS_DESCRIPTOR_in_type_descriptor2388 = new BitSet(new long[]{2L});
//        FOLLOW_array_descriptor_in_type_descriptor2394 = new BitSet(new long[]{2L});
//        FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor2404 = new BitSet(new long[]{2L});
//        FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor2410 = new BitSet(new long[]{2L});
//        FOLLOW_array_descriptor_in_nonvoid_type_descriptor2416 = new BitSet(new long[]{2L});
//        FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor2426 = new BitSet(new long[]{2L});
//        FOLLOW_array_descriptor_in_reference_type_descriptor2432 = new BitSet(new long[]{2L});
//        FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2442 = new BitSet(new long[]{2L});
//        FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2453 = new BitSet(new long[]{2L});
//        FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2468 = new BitSet(new long[]{2L});
//        FOLLOW_FLOAT_LITERAL_in_float_literal2479 = new BitSet(new long[]{2L});
//        FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2489 = new BitSet(new long[]{2L});
//        FOLLOW_DOUBLE_LITERAL_in_double_literal2500 = new BitSet(new long[]{2L});
//        FOLLOW_LONG_LITERAL_in_literal2510 = new BitSet(new long[]{2L});
//        FOLLOW_integer_literal_in_literal2516 = new BitSet(new long[]{2L});
//        FOLLOW_SHORT_LITERAL_in_literal2522 = new BitSet(new long[]{2L});
//        FOLLOW_BYTE_LITERAL_in_literal2528 = new BitSet(new long[]{2L});
//        FOLLOW_float_literal_in_literal2534 = new BitSet(new long[]{2L});
//        FOLLOW_double_literal_in_literal2540 = new BitSet(new long[]{2L});
//        FOLLOW_CHAR_LITERAL_in_literal2546 = new BitSet(new long[]{2L});
//        FOLLOW_STRING_LITERAL_in_literal2552 = new BitSet(new long[]{2L});
//        FOLLOW_BOOL_LITERAL_in_literal2558 = new BitSet(new long[]{2L});
//        FOLLOW_NULL_LITERAL_in_literal2564 = new BitSet(new long[]{2L});
//        FOLLOW_array_literal_in_literal2570 = new BitSet(new long[]{2L});
//        FOLLOW_subannotation_in_literal2576 = new BitSet(new long[]{2L});
//        FOLLOW_type_field_method_literal_in_literal2582 = new BitSet(new long[]{2L});
//        FOLLOW_enum_literal_in_literal2588 = new BitSet(new long[]{2L});
//        FOLLOW_integer_literal_in_parsed_integer_literal2601 = new BitSet(new long[]{2L});
//        FOLLOW_LONG_LITERAL_in_integral_literal2613 = new BitSet(new long[]{2L});
//        FOLLOW_integer_literal_in_integral_literal2619 = new BitSet(new long[]{2L});
//        FOLLOW_SHORT_LITERAL_in_integral_literal2625 = new BitSet(new long[]{2L});
//        FOLLOW_CHAR_LITERAL_in_integral_literal2631 = new BitSet(new long[]{2L});
//        FOLLOW_BYTE_LITERAL_in_integral_literal2637 = new BitSet(new long[]{2L});
//        FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2647 = new BitSet(new long[]{2L});
//        FOLLOW_integer_literal_in_fixed_32bit_literal2653 = new BitSet(new long[]{2L});
//        FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2659 = new BitSet(new long[]{2L});
//        FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2665 = new BitSet(new long[]{2L});
//        FOLLOW_float_literal_in_fixed_32bit_literal2671 = new BitSet(new long[]{2L});
//        FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2677 = new BitSet(new long[]{2L});
//        FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2683 = new BitSet(new long[]{2L});
//        FOLLOW_integer_literal_in_fixed_literal2693 = new BitSet(new long[]{2L});
//        FOLLOW_LONG_LITERAL_in_fixed_literal2699 = new BitSet(new long[]{2L});
//        FOLLOW_SHORT_LITERAL_in_fixed_literal2705 = new BitSet(new long[]{2L});
//        FOLLOW_BYTE_LITERAL_in_fixed_literal2711 = new BitSet(new long[]{2L});
//        FOLLOW_float_literal_in_fixed_literal2717 = new BitSet(new long[]{2L});
//        FOLLOW_double_literal_in_fixed_literal2723 = new BitSet(new long[]{2L});
//        FOLLOW_CHAR_LITERAL_in_fixed_literal2729 = new BitSet(new long[]{2L});
//        FOLLOW_BOOL_LITERAL_in_fixed_literal2735 = new BitSet(new long[]{2L});
//        FOLLOW_OPEN_BRACE_in_array_literal2745 = new BitSet(new long[]{-3316517662275482288L, 34582967L, -5121718676227096576L, 1740L});
//        FOLLOW_literal_in_array_literal2748 = new BitSet(new long[]{1179648L});
//        FOLLOW_COMMA_in_array_literal2751 = new BitSet(new long[]{-3316517662275613360L, 34582967L, -5121718676227096576L, 1740L});
//        FOLLOW_literal_in_array_literal2753 = new BitSet(new long[]{1179648L});
//        FOLLOW_CLOSE_BRACE_in_array_literal2761 = new BitSet(new long[]{2L});
//        FOLLOW_simple_name_in_annotation_element2785 = new BitSet(new long[]{34359738368L});
//        FOLLOW_EQUAL_in_annotation_element2787 = new BitSet(new long[]{-3316517662275613360L, 34582967L, -5121718676227096576L, 1740L});
//        FOLLOW_literal_in_annotation_element2789 = new BitSet(new long[]{2L});
//        FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2814 = new BitSet(new long[]{64L});
//        FOLLOW_ANNOTATION_VISIBILITY_in_annotation2816 = new BitSet(new long[]{32768L});
//        FOLLOW_CLASS_DESCRIPTOR_in_annotation2818 = new BitSet(new long[]{-3316517945730923440L, 34582967L, -5161125172966588416L, 1544L});
//        FOLLOW_annotation_element_in_annotation2824 = new BitSet(new long[]{-3316517945730923440L, 34582967L, -5161125172966588416L, 1544L});
//        FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2827 = new BitSet(new long[]{2L});
//        FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation2860 = new BitSet(new long[]{32768L});
//        FOLLOW_CLASS_DESCRIPTOR_in_subannotation2862 = new BitSet(new long[]{-3316517941452733360L, 34582967L, -5161125172966588416L, 1544L});
//        FOLLOW_annotation_element_in_subannotation2864 = new BitSet(new long[]{-3316517941452733360L, 34582967L, -5161125172966588416L, 1544L});
//        FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation2867 = new BitSet(new long[]{2L});
//        FOLLOW_ENUM_DIRECTIVE_in_enum_literal2894 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_field_reference_in_enum_literal2896 = new BitSet(new long[]{2L});
//        FOLLOW_reference_type_descriptor_in_type_field_method_literal2916 = new BitSet(new long[]{2L});
//        FOLLOW_reference_type_descriptor_in_type_field_method_literal2925 = new BitSet(new long[]{512L});
//        FOLLOW_ARROW_in_type_field_method_literal2927 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_member_name_in_type_field_method_literal2939 = new BitSet(new long[]{524288L});
//        FOLLOW_COLON_in_type_field_method_literal2941 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal2943 = new BitSet(new long[]{2L});
//        FOLLOW_member_name_in_type_field_method_literal2966 = new BitSet(new long[]{0L, 0L, 72057594037927936L});
//        FOLLOW_method_prototype_in_type_field_method_literal2968 = new BitSet(new long[]{2L});
//        FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3001 = new BitSet(new long[]{2L});
//        FOLLOW_VOID_TYPE_in_type_field_method_literal3007 = new BitSet(new long[]{2L});
//        FOLLOW_reference_type_descriptor_in_method_reference3018 = new BitSet(new long[]{512L});
//        FOLLOW_ARROW_in_method_reference3020 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_member_name_in_method_reference3024 = new BitSet(new long[]{0L, 0L, 72057594037927936L});
//        FOLLOW_method_prototype_in_method_reference3026 = new BitSet(new long[]{2L});
//        FOLLOW_reference_type_descriptor_in_field_reference3048 = new BitSet(new long[]{512L});
//        FOLLOW_ARROW_in_field_reference3050 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_member_name_in_field_reference3054 = new BitSet(new long[]{524288L});
//        FOLLOW_COLON_in_field_reference3056 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_field_reference3058 = new BitSet(new long[]{2L});
//        FOLLOW_COLON_in_label3079 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5161125172966588416L, 1544L});
//        FOLLOW_simple_name_in_label3081 = new BitSet(new long[]{2L});
//        FOLLOW_COLON_in_label_ref3100 = new BitSet(new long[]{-3316517945747700656L, 34582967L, -5161125172966588416L, 1544L});
//        FOLLOW_simple_name_in_label_ref3102 = new BitSet(new long[]{2L});
//        FOLLOW_REGISTER_in_register_list3116 = new BitSet(new long[]{1048578L});
//        FOLLOW_COMMA_in_register_list3119 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_register_list3121 = new BitSet(new long[]{1048578L});
//        FOLLOW_REGISTER_in_register_range3156 = new BitSet(new long[]{2097154L});
//        FOLLOW_DOTDOT_in_register_range3159 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_register_range3163 = new BitSet(new long[]{2L});
//        FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3192 = new BitSet(new long[]{2L});
//        FOLLOW_field_reference_in_verification_error_reference3196 = new BitSet(new long[]{2L});
//        FOLLOW_method_reference_in_verification_error_reference3200 = new BitSet(new long[]{2L});
//        FOLLOW_CATCH_DIRECTIVE_in_catch_directive3210 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_catch_directive3212 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_catch_directive3214 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_catch_directive3218 = new BitSet(new long[]{2097152L});
//        FOLLOW_DOTDOT_in_catch_directive3220 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_catch_directive3224 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_catch_directive3226 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_catch_directive3230 = new BitSet(new long[]{2L});
//        FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3262 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_catchall_directive3264 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_catchall_directive3268 = new BitSet(new long[]{2097152L});
//        FOLLOW_DOTDOT_in_catchall_directive3270 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_catchall_directive3274 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_catchall_directive3276 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_catchall_directive3280 = new BitSet(new long[]{2L});
//        FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3319 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_parameter_directive3321 = new BitSet(new long[]{1074790434L});
//        FOLLOW_COMMA_in_parameter_directive3324 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_parameter_directive3326 = new BitSet(new long[]{1073741858L});
//        FOLLOW_annotation_in_parameter_directive3337 = new BitSet(new long[]{1073741858L});
//        FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3350 = new BitSet(new long[]{2L});
//        FOLLOW_line_directive_in_debug_directive3423 = new BitSet(new long[]{2L});
//        FOLLOW_local_directive_in_debug_directive3429 = new BitSet(new long[]{2L});
//        FOLLOW_end_local_directive_in_debug_directive3435 = new BitSet(new long[]{2L});
//        FOLLOW_restart_local_directive_in_debug_directive3441 = new BitSet(new long[]{2L});
//        FOLLOW_prologue_directive_in_debug_directive3447 = new BitSet(new long[]{2L});
//        FOLLOW_epilogue_directive_in_debug_directive3453 = new BitSet(new long[]{2L});
//        FOLLOW_source_directive_in_debug_directive3459 = new BitSet(new long[]{2L});
//        FOLLOW_LINE_DIRECTIVE_in_line_directive3469 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_line_directive3471 = new BitSet(new long[]{2L});
//        FOLLOW_LOCAL_DIRECTIVE_in_local_directive3494 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_local_directive3496 = new BitSet(new long[]{1048578L});
//        FOLLOW_COMMA_in_local_directive3499 = new BitSet(new long[]{0L, 0L, 18014398509481984L, 64L});
//        FOLLOW_NULL_LITERAL_in_local_directive3502 = new BitSet(new long[]{524288L});
//        FOLLOW_STRING_LITERAL_in_local_directive3508 = new BitSet(new long[]{524288L});
//        FOLLOW_COLON_in_local_directive3511 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L, 1024L});
//        FOLLOW_VOID_TYPE_in_local_directive3514 = new BitSet(new long[]{1048578L});
//        FOLLOW_nonvoid_type_descriptor_in_local_directive3518 = new BitSet(new long[]{1048578L});
//        FOLLOW_COMMA_in_local_directive3552 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_local_directive3556 = new BitSet(new long[]{2L});
//        FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3598 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_end_local_directive3600 = new BitSet(new long[]{2L});
//        FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3623 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_restart_local_directive3625 = new BitSet(new long[]{2L});
//        FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3648 = new BitSet(new long[]{2L});
//        FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3669 = new BitSet(new long[]{2L});
//        FOLLOW_SOURCE_DIRECTIVE_in_source_directive3690 = new BitSet(new long[]{2L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_source_directive3692 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x3717 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x3723 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s3738 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s3744 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i3759 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i3765 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format10t_in_instruction3782 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format10x_in_instruction3788 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format10x_odex_in_instruction3794 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format11n_in_instruction3800 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format11x_in_instruction3806 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format12x_in_instruction3812 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format20bc_in_instruction3818 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format20t_in_instruction3824 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21c_field_in_instruction3830 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21c_field_odex_in_instruction3836 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21c_string_in_instruction3842 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21c_type_in_instruction3848 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21c_lambda_in_instruction3854 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21c_method_in_instruction3860 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21ih_in_instruction3866 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21lh_in_instruction3872 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21s_in_instruction3878 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format21t_in_instruction3884 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22b_in_instruction3890 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22c_field_in_instruction3896 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22c_field_odex_in_instruction3902 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22c_type_in_instruction3908 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22c_string_in_instruction3914 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22cs_field_in_instruction3920 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22s_in_instruction3926 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22t_in_instruction3932 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format22x_in_instruction3938 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format23x_in_instruction3944 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format25x_in_instruction3950 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format30t_in_instruction3956 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format31c_in_instruction3962 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format31i_in_instruction3968 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format31t_in_instruction3974 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format32x_in_instruction3980 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format35c_method_in_instruction3986 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format35c_type_in_instruction3992 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format35c_method_odex_in_instruction3998 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format35mi_method_in_instruction4004 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format35ms_method_in_instruction4010 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format3rc_method_in_instruction4016 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format3rc_method_odex_in_instruction4022 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format3rc_type_in_instruction4028 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format3rmi_method_in_instruction4034 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format3rms_method_in_instruction4040 = new BitSet(new long[]{2L});
//        FOLLOW_insn_format51l_in_instruction4046 = new BitSet(new long[]{2L});
//        FOLLOW_insn_array_data_directive_in_instruction4052 = new BitSet(new long[]{2L});
//        FOLLOW_insn_packed_switch_directive_in_instruction4058 = new BitSet(new long[]{2L});
//        FOLLOW_insn_sparse_switch_directive_in_instruction4064 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4084 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_format10t4086 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4116 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4144 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4165 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format11n4167 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format11n4169 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_insn_format11n4171 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4203 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format11x4205 = new BitSet(new long[]{2L});
//        FOLLOW_instruction_format12x_in_insn_format12x4235 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format12x4237 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format12x4239 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format12x4241 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4273 = new BitSet(new long[]{0L, 0L, 0L, 512L});
//        FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4275 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format20bc4277 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_verification_error_reference_in_insn_format20bc4279 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4316 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_format20t4318 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4348 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21c_field4350 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21c_field4352 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_field_reference_in_insn_format21c_field4354 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4386 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21c_field_odex4388 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21c_field_odex4390 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_field_reference_in_insn_format21c_field_odex4392 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4430 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21c_string4432 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21c_string4434 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_insn_format21c_string4436 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4468 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21c_type4470 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21c_type4472 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4474 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda4506 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21c_lambda4508 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21c_lambda4510 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_insn_format21c_lambda4512 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method4544 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21c_method4546 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21c_method4548 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_method_reference_in_insn_format21c_method4550 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4582 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21ih4584 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21ih4586 = new BitSet(new long[]{824633740288L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_32bit_literal_in_insn_format21ih4588 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4620 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21lh4622 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21lh4624 = new BitSet(new long[]{824633740288L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_32bit_literal_in_insn_format21lh4626 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s4658 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21s4660 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21s4662 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_insn_format21s4664 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t4696 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format21t4698 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format21t4700 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_format21t4702 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b4734 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22b4736 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22b4738 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22b4740 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22b4742 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_insn_format22b4744 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field4778 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_field4780 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_field4782 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_field4784 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_field4786 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_field_reference_in_insn_format22c_field4788 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex4822 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_field_odex4824 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_field_odex4826 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_field_odex4828 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_field_odex4830 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_field_reference_in_insn_format22c_field_odex4832 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type4872 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_type4874 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_type4876 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_type4878 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_type4880 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type4882 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string4916 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_string4918 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_string4920 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22c_string4922 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22c_string4924 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_insn_format22c_string4926 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field4960 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22cs_field4962 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22cs_field4964 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22cs_field4966 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22cs_field4968 = new BitSet(new long[]{137438953472L});
//        FOLLOW_FIELD_OFFSET_in_insn_format22cs_field4970 = new BitSet(new long[]{2L});
//        FOLLOW_instruction_format22s_in_insn_format22s4991 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22s4993 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22s4995 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22s4997 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22s4999 = new BitSet(new long[]{18432L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_integral_literal_in_insn_format22s5001 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5035 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22t5037 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22t5039 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22t5041 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22t5043 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_format22t5045 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5079 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22x5081 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format22x5083 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format22x5085 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5117 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format23x5119 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format23x5121 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format23x5123 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format23x5125 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format23x5127 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x5161 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format25x5163 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format25x5165 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format25x5167 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format25x5169 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format25x5171 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5203 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_format30t5205 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5235 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format31c5237 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format31c5239 = new BitSet(new long[]{0L, 0L, 0L, 64L});
//        FOLLOW_STRING_LITERAL_in_insn_format31c5241 = new BitSet(new long[]{2L});
//        FOLLOW_instruction_format31i_in_insn_format31i5272 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format31i5274 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format31i5276 = new BitSet(new long[]{824633740288L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_32bit_literal_in_insn_format31i5278 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5310 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format31t5312 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format31t5314 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_format31t5316 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5348 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format32x5350 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format32x5352 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format32x5354 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method5386 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format35c_method5388 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format35c_method5390 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format35c_method5392 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format35c_method5394 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_method_reference_in_insn_format35c_method5396 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5428 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format35c_type5430 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format35c_type5432 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format35c_type5434 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format35c_type5436 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5438 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5470 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5472 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format35c_method_odex5474 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5476 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format35c_method_odex5478 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_method_reference_in_insn_format35c_method_odex5480 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5501 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format35mi_method5503 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format35mi_method5505 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5507 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format35mi_method5509 = new BitSet(new long[]{2199023255552L});
//        FOLLOW_INLINE_INDEX_in_insn_format35mi_method5511 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5532 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format35ms_method5534 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format35ms_method5536 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5538 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format35ms_method5540 = new BitSet(new long[]{0L, 0L, 0L, 2048L});
//        FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5542 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5563 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format3rc_method5565 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_range_in_insn_format3rc_method5567 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5569 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format3rc_method5571 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_method_reference_in_insn_format3rc_method5573 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5605 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5607 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_list_in_insn_format3rc_method_odex5609 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5611 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format3rc_method_odex5613 = new BitSet(new long[]{-3316517945747667632L, 34582967L, -5158873373152903168L, 1544L});
//        FOLLOW_method_reference_in_insn_format3rc_method_odex5615 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5636 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format3rc_type5638 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_range_in_insn_format3rc_type5640 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5642 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format3rc_type5644 = new BitSet(new long[]{33024L, 0L, 2305843009213693952L});
//        FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5646 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method5678 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format3rmi_method5680 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_range_in_insn_format3rmi_method5682 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method5684 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format3rmi_method5686 = new BitSet(new long[]{2199023255552L});
//        FOLLOW_INLINE_INDEX_in_insn_format3rmi_method5688 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method5709 = new BitSet(new long[]{0L, 0L, 36028797018963968L});
//        FOLLOW_OPEN_BRACE_in_insn_format3rms_method5711 = new BitSet(new long[]{131072L, 0L, -9223372036854775808L});
//        FOLLOW_register_range_in_insn_format3rms_method5713 = new BitSet(new long[]{131072L});
//        FOLLOW_CLOSE_BRACE_in_insn_format3rms_method5715 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format3rms_method5717 = new BitSet(new long[]{0L, 0L, 0L, 2048L});
//        FOLLOW_VTABLE_INDEX_in_insn_format3rms_method5719 = new BitSet(new long[]{2L});
//        FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l5740 = new BitSet(new long[]{0L, 0L, -9223372036854775808L});
//        FOLLOW_REGISTER_in_insn_format51l5742 = new BitSet(new long[]{1048576L});
//        FOLLOW_COMMA_in_insn_format51l5744 = new BitSet(new long[]{824646323200L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_literal_in_insn_format51l5746 = new BitSet(new long[]{2L});
//        FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5773 = new BitSet(new long[]{0L, 0L, 1161928703861587968L});
//        FOLLOW_parsed_integer_literal_in_insn_array_data_directive5779 = new BitSet(new long[]{824679877632L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_literal_in_insn_array_data_directive5791 = new BitSet(new long[]{824679877632L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5794 = new BitSet(new long[]{2L});
//        FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5840 = new BitSet(new long[]{824633740288L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive5846 = new BitSet(new long[]{537395200L});
//        FOLLOW_label_ref_in_insn_packed_switch_directive5852 = new BitSet(new long[]{537395200L});
//        FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5859 = new BitSet(new long[]{2L});
//        FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5933 = new BitSet(new long[]{826781223936L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive5940 = new BitSet(new long[]{512L});
//        FOLLOW_ARROW_in_insn_sparse_switch_directive5942 = new BitSet(new long[]{524288L});
//        FOLLOW_label_ref_in_insn_sparse_switch_directive5944 = new BitSet(new long[]{826781223936L, 0L, 1163054603768430592L, 4L});
//        FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5952 = new BitSet(new long[]{2L});
//    }
//
//    protected class DFA40 extends DFA {
//        public DFA40(BaseRecognizer recognizer) {
//            this.recognizer = recognizer;
//            this.decisionNumber = 40;
//            this.eot = smaliParser.DFA40_eot;
//            this.eof = smaliParser.DFA40_eof;
//            this.min = smaliParser.DFA40_min;
//            this.max = smaliParser.DFA40_max;
//            this.accept = smaliParser.DFA40_accept;
//            this.special = smaliParser.DFA40_special;
//            this.transition = smaliParser.DFA40_transition;
//        }
//
//        public String getDescription() {
//            return "()* loopback of 747:5: ({...}? annotation )*";
//        }
//
//        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
//            TokenStream input = (TokenStream)_input;
//            switch(s) {
//                case 0:
//                    int LA40_67 = input.LA(1);
//                    int index40_67 = input.index();
//                    input.rewind();
//                    if (input.LA(1) == 5) {
//                        s = 68;
//                    } else {
//                        s = 1;
//                    }
//
//                    input.seek(index40_67);
//                    if (s >= 0) {
//                        return s;
//                    }
//                default:
//                    NoViableAltException nvae = new NoViableAltException(this.getDescription(), 40, s, input);
//                    this.error(nvae);
//                    throw nvae;
//            }
//        }
//    }
//
//    protected class DFA38 extends DFA {
//        public DFA38(BaseRecognizer recognizer) {
//            this.recognizer = recognizer;
//            this.decisionNumber = 38;
//            this.eot = smaliParser.DFA38_eot;
//            this.eof = smaliParser.DFA38_eof;
//            this.min = smaliParser.DFA38_min;
//            this.max = smaliParser.DFA38_max;
//            this.accept = smaliParser.DFA38_accept;
//            this.special = smaliParser.DFA38_special;
//            this.transition = smaliParser.DFA38_transition;
//        }
//
//        public String getDescription() {
//            return "729:1: verification_error_reference : ( CLASS_DESCRIPTOR | field_reference | method_reference );";
//        }
//    }
//
//    protected class DFA30 extends DFA {
//        public DFA30(BaseRecognizer recognizer) {
//            this.recognizer = recognizer;
//            this.decisionNumber = 30;
//            this.eot = smaliParser.DFA30_eot;
//            this.eof = smaliParser.DFA30_eof;
//            this.min = smaliParser.DFA30_min;
//            this.max = smaliParser.DFA30_max;
//            this.accept = smaliParser.DFA30_accept;
//            this.special = smaliParser.DFA30_special;
//            this.transition = smaliParser.DFA30_transition;
//        }
//
//        public String getDescription() {
//            return "701:7: ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )";
//        }
//    }
//
//    public static class insn_sparse_switch_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_sparse_switch_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_packed_switch_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_packed_switch_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_array_data_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_array_data_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format51l_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format51l_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format3rms_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format3rms_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format3rmi_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format3rmi_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format3rc_type_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format3rc_type_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format3rc_method_odex_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format3rc_method_odex_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format3rc_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format3rc_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format35ms_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format35ms_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format35mi_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format35mi_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format35c_method_odex_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format35c_method_odex_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format35c_type_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format35c_type_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format35c_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format35c_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format32x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format32x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format31t_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format31t_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format31i_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format31i_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format31c_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format31c_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format30t_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format30t_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format25x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format25x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format23x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format23x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22t_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22t_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22s_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22s_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22cs_field_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22cs_field_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22c_string_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22c_string_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22c_type_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22c_type_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22c_field_odex_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22c_field_odex_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22c_field_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22c_field_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format22b_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format22b_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21t_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21t_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21s_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21s_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21lh_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21lh_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21ih_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21ih_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21c_method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21c_method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21c_lambda_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21c_lambda_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21c_type_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21c_type_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21c_string_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21c_string_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21c_field_odex_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21c_field_odex_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format21c_field_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format21c_field_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format20t_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format20t_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format20bc_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format20bc_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format12x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format12x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format11x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format11x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format11n_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format11n_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format10x_odex_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format10x_odex_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format10x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format10x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class insn_format10t_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public insn_format10t_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class instruction_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public instruction_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class instruction_format31i_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public instruction_format31i_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class instruction_format22s_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public instruction_format22s_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class instruction_format12x_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public instruction_format12x_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class source_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public source_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class epilogue_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public epilogue_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class prologue_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public prologue_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class restart_local_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public restart_local_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class end_local_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public end_local_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class local_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public local_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class line_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public line_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class debug_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public debug_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class parameter_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public parameter_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class catchall_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public catchall_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class catch_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public catch_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class verification_error_reference_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public verification_error_reference_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class register_range_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public register_range_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class register_list_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public register_list_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class label_ref_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public label_ref_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class label_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public label_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class field_reference_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public field_reference_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class method_reference_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public method_reference_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class type_field_method_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public type_field_method_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class enum_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public enum_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class subannotation_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public subannotation_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class annotation_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public annotation_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class annotation_element_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public annotation_element_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class array_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public array_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class fixed_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public fixed_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class fixed_32bit_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public fixed_32bit_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class integral_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public integral_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class parsed_integer_literal_return extends ParserRuleReturnScope {
//        public int value;
//        CommonTree tree;
//
//        public parsed_integer_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class double_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public double_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class float_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public float_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class integer_literal_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public integer_literal_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class reference_type_descriptor_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public reference_type_descriptor_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class nonvoid_type_descriptor_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public nonvoid_type_descriptor_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class type_descriptor_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public type_descriptor_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class array_descriptor_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public array_descriptor_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class param_list_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public param_list_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class param_list_or_id_primitive_type_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public param_list_or_id_primitive_type_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class method_prototype_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public method_prototype_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class member_name_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public member_name_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class simple_name_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public simple_name_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class param_list_or_id_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public param_list_or_id_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class registers_directive_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public registers_directive_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class ordered_method_item_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public ordered_method_item_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class statements_and_directives_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public statements_and_directives_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    protected static class statements_and_directives_scope {
//        boolean hasRegistersDirective;
//        List<CommonTree> methodAnnotations;
//
//        protected statements_and_directives_scope() {
//        }
//    }
//
//    public static class method_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public method_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class field_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public field_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class access_list_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public access_list_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class source_spec_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public source_spec_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class implements_spec_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public implements_spec_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class super_spec_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public super_spec_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class class_spec_return extends ParserRuleReturnScope {
//        public String className;
//        CommonTree tree;
//
//        public class_spec_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    public static class smali_file_return extends ParserRuleReturnScope {
//        CommonTree tree;
//
//        public smali_file_return() {
//        }
//
//        public CommonTree getTree() {
//            return this.tree;
//        }
//    }
//
//    protected static class smali_file_scope {
//        boolean hasClassSpec;
//        boolean hasSuperSpec;
//        boolean hasSourceSpec;
//        List<CommonTree> classAnnotations;
//
//        protected smali_file_scope() {
//        }
//    }
//}
