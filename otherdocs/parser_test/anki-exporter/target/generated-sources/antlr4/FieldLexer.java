// Generated from Field.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FieldLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Name=7, Int=8, WS=9, NL=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "Name", "Int", "WS", "NL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'scores'", "'points'", "'is'", "'buried'", "'at'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "Name", "Int", "WS", "NL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FieldLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Field.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\fK\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\6\b\67\n\b"+
		"\r\b\16\b8\3\b\3\b\3\t\6\t>\n\t\r\t\16\t?\3\n\6\nC\n\n\r\n\16\nD\3\13"+
		"\5\13H\n\13\3\13\3\13\2\2\f\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\3\2\4\5\2\"\"C\\c|\4\2\13\13\"\"\2N\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\36\3\2\2\2\7%\3\2\2\2\t(\3\2\2"+
		"\2\13/\3\2\2\2\r\62\3\2\2\2\17\64\3\2\2\2\21=\3\2\2\2\23B\3\2\2\2\25G"+
		"\3\2\2\2\27\30\7u\2\2\30\31\7e\2\2\31\32\7q\2\2\32\33\7t\2\2\33\34\7g"+
		"\2\2\34\35\7u\2\2\35\4\3\2\2\2\36\37\7r\2\2\37 \7q\2\2 !\7k\2\2!\"\7p"+
		"\2\2\"#\7v\2\2#$\7u\2\2$\6\3\2\2\2%&\7k\2\2&\'\7u\2\2\'\b\3\2\2\2()\7"+
		"d\2\2)*\7w\2\2*+\7t\2\2+,\7k\2\2,-\7g\2\2-.\7f\2\2.\n\3\2\2\2/\60\7c\2"+
		"\2\60\61\7v\2\2\61\f\3\2\2\2\62\63\7.\2\2\63\16\3\2\2\2\64\66\7$\2\2\65"+
		"\67\t\2\2\2\66\65\3\2\2\2\678\3\2\2\28\66\3\2\2\289\3\2\2\29:\3\2\2\2"+
		":;\7$\2\2;\20\3\2\2\2<>\4\62;\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2"+
		"\2@\22\3\2\2\2AC\t\3\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\24\3"+
		"\2\2\2FH\7\17\2\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\f\2\2J\26\3\2\2\2"+
		"\7\28?DG\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}