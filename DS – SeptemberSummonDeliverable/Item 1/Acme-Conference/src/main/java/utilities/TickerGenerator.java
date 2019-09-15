
package utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDate;

import domain.Author;

public class TickerGenerator {

	public static String generateTicker() {
		final List<String> alpha = new ArrayList<String>();
		alpha.add("A");
		alpha.add("B");
		alpha.add("C");
		alpha.add("D");
		alpha.add("E");
		alpha.add("F");
		alpha.add("G");
		alpha.add("H");
		alpha.add("I");
		alpha.add("J");
		alpha.add("K");
		alpha.add("L");
		alpha.add("M");
		alpha.add("N");
		alpha.add("O");
		alpha.add("P");
		alpha.add("Q");
		alpha.add("R");
		alpha.add("S");
		alpha.add("T");
		alpha.add("U");
		alpha.add("V");
		alpha.add("W");
		alpha.add("X");
		alpha.add("Y");
		alpha.add("Z");

		final List<String> nums = new ArrayList<String>();
		nums.add("0");
		nums.add("1");
		nums.add("2");
		nums.add("3");
		nums.add("4");
		nums.add("5");
		nums.add("6");
		nums.add("7");
		nums.add("8");
		nums.add("9");

		final int day1 = Calendar.getInstance().getTime().getDate();
		final String day = String.valueOf(day1);
		System.out.println("day " + day1);
		final int year1 = Calendar.getInstance().getTime().getYear();
		final String year = String.valueOf(year1);
		System.out.println("year " + year1);
		final int month1 = Calendar.getInstance().getTime().getMonth();

		final String month = String.valueOf(month1);
		System.out.println("month " + month1);

		final String bueno = LocalDate.now().toString();
		final String bueno2 = bueno.replace("-", "");
		final String bueno3 = bueno2.substring(2);

		String ticker = bueno3;

		ticker = ticker + "-";
		for (Integer i = 0; i < 6; i++) {
			final Integer selector = new Random().nextInt(26);
			if (selector > 14) {
				final Integer letra = new Random().nextInt(26);
				final String a = alpha.get(letra);
				ticker = ticker + a;
			} else {
				final Integer numero = new Random().nextInt(9);
				final String b = nums.get(numero);
				ticker = ticker + b;
			}
		}
		return ticker;
	}

	public static String generateTicker2() {
		final List<String> alpha = new ArrayList<String>();
		alpha.add("A");
		alpha.add("B");
		alpha.add("C");
		alpha.add("D");
		alpha.add("E");
		alpha.add("F");
		alpha.add("G");
		alpha.add("H");
		alpha.add("I");
		alpha.add("J");
		alpha.add("K");
		alpha.add("L");
		alpha.add("M");
		alpha.add("N");
		alpha.add("O");
		alpha.add("P");
		alpha.add("Q");
		alpha.add("R");
		alpha.add("S");
		alpha.add("T");
		alpha.add("U");
		alpha.add("V");
		alpha.add("W");
		alpha.add("X");
		alpha.add("Y");
		alpha.add("Z");

		final List<String> nums = new ArrayList<String>();
		nums.add("0");
		nums.add("1");
		nums.add("2");
		nums.add("3");
		nums.add("4");
		nums.add("5");
		nums.add("6");
		nums.add("7");
		nums.add("8");
		nums.add("9");

		final int day1 = Calendar.getInstance().getTime().getDate();
		final String day = String.valueOf(day1);
		System.out.println("day " + day1);
		final int year1 = Calendar.getInstance().getTime().getYear();
		final String year = String.valueOf(year1);
		System.out.println("year " + year1);
		final int month1 = Calendar.getInstance().getTime().getMonth();

		final String month = String.valueOf(month1);
		System.out.println("month " + month1);

		final String bueno = LocalDate.now().toString();
		System.out.println("bueno" + bueno);
		final String bueno2 = bueno.replace("-", "");
		final String bueno3 = bueno2.substring(2);

		final String ticker = bueno3;
		final String añoControl = year.substring(1);

		System.out.println("AÑO" + añoControl);
		System.out.println("MES" + month);
		System.out.println("DIA" + day);
		String monthControl = month;
		if (month.length() == 1)
			monthControl = "0".concat(month);

		final String tickerControl = añoControl + "-" + monthControl + day + "-";

		Integer numero = new Random().nextInt(9999);

		while (numero < 10)
			numero = new Random().nextInt(9999);

		final String tickerControl1 = tickerControl.concat(numero.toString());

		//		ticker = ticker + "-";
		//		for (Integer i = 0; i < 6; i++) {
		//			final Integer numero = new Random().nextInt(9);
		//			final String b = nums.get(numero);
		//			ticker = ticker + b;
		//		}
		return tickerControl1;
	}

	public static void main(final String[] args) {

		final String tickerFinal = TickerGenerator.generateTicker2();

		System.out.println("Ejemplo de ticket " + tickerFinal);

		//		final String todo = "a acá ahí ajena/o/s al algo algún/a/o/s allá/í ambos ante antes aquel aquella/o/s aquí arriba así atrás aun aunque bajo bastante bien cabe cada casi cierto/a/s como con conmigo conseguimos conseguir consigo consigue consiguen consigues contigo contra cual cuales cualquier/a/s cuan cuando cuanto/a/s de dejar del demás demasiada/o/s dentro desde donde dos el él ella/o/s empleáis emplean emplear empleas empleo en encima entonces entre era/s eramos eran eres es esa/e/o/s esta/s estaba estado estáis estamos están estar este/o/s estoy etc fin fue fueron fui fuimos gueno ha hace/s hacéis hacemos hacen hacer hacia hago hasta incluso intenta/s intentáis intentamos intentan intentar intento ir jamás junto/s la/o/s largo más me menos mi/s mía/s mientras mío/s misma/o/s modo mucha/s muchísima/o/s mucho/s muy nada ni ningún/a/o/s no nos nosotras/os nuestra/o/s nunca os otra/o/s para parecer pero poca/o/s podéis podemos poder podría/s podríais podríamos podrían por por qué porque primero puede/n puedo pues que qué querer quién/es quienesquiera quienquiera quizá/s sabe/s/n sabéis sabemos saber se según ser si sí siempre siendo sin sino so sobre sois solamente solo sólo somos soy sr sra sres sta su/s suya/o/s tal/es también tampoco tanta/o/s te tenéis tenemos tener tengo ti tiempo tiene tienen toda/o/s tomar trabaja/o trabajáis trabajamos trabajan trabajar trabajas tras tú tu tus tuya/o/s último ultimo un/a/o/s usa/s usáis usamos usan usar uso usted/es va/n vais valor vamos varias/os vaya verdadera vosotras/os voy vuestra/o/s y ya yo";
		//		final String[] splite = todo.split(" ");
		//		String res = "<value>";
		//		for (final String s : splite)
		//			res = res + s + "</value> \n <value>";

		//		final String todo = "'tis, 'twas, a, able, about, across, after, ain't, all, almost, also, am, among, an, and, any, are, aren't, as, at, be, because, been, but, by, can, can't, cannot, could, could've, couldn't, dear, did, didn't, do, does, doesn't, don't, either, else, ever, every, for, from, get, got, had, has, hasn't, have, he, he'd, he'll, he's, her, hers, him, his, how, how'd, how'll, how's, however, i, i'd, i'll, i'm, i've, if, in, into, is, isn't, it, it's, its, just, least, let, like, likely, may, me, might, might've, mightn't, most, must, must've, mustn't, my, neither, no, nor, not, of, off, often, on, only, or, other, our, own, rather, said, say, says, shan't, she, she'd, she'll, she's, should, should've, shouldn't, since, so, some, than, that, that'll, that's, the, their, them, then, there, there's, these, they, they'd, they'll, they're, they've, this, tis, to, too, twas, us, wants, was, wasn't, we, we'd, we'll, we're, were, weren't, what, what'd, what's, when, when, when'd, when'll, when's, where, where'd, where'll, where's, which, while, who, who'd, who'll, who's, whom, why, why'd, why'll, why's, will, with, won't, would, would've, wouldn't, yet, you, you'd, you'll, you're, you've, your";
		//		final String[] splite = todo.split(", ");
		//		String res = "<value>";
		//		for (final String s : splite)
		//			res = res + s + "</value> \n <value>";
		//		System.out.println(res);

		final List<String> ostia = new ArrayList<String>();
		ostia.add("Conference");
		ostia.add("about");
		ostia.add("damn");
		final List<String> joder = new ArrayList<String>();
		joder.add("about");
		joder.add("love");

		ostia.removeAll(joder);

		System.out.println("hola");
	}
	public static String tickerLeave() {

		final List<String> alpha = new ArrayList<String>();
		alpha.add("A");
		alpha.add("B");
		alpha.add("C");
		alpha.add("D");
		alpha.add("E");
		alpha.add("F");
		alpha.add("G");
		alpha.add("H");
		alpha.add("I");
		alpha.add("J");
		alpha.add("K");
		alpha.add("L");
		alpha.add("M");
		alpha.add("N");
		alpha.add("O");
		alpha.add("P");
		alpha.add("Q");
		alpha.add("R");
		alpha.add("S");
		alpha.add("T");
		alpha.add("U");
		alpha.add("V");
		alpha.add("W");
		alpha.add("X");
		alpha.add("Y");
		alpha.add("Z");

		final List<String> nums = new ArrayList<String>();
		nums.add("0");
		nums.add("1");
		nums.add("2");
		nums.add("3");
		nums.add("4");
		nums.add("5");
		nums.add("6");
		nums.add("7");
		nums.add("8");
		nums.add("9");

		String ticker = "";

		for (Integer i = 0; i < 6; i++) {
			final Integer selector = new Random().nextInt(26);
			if (selector > 14) {
				final Integer letra = new Random().nextInt(26);
				final String a = alpha.get(letra);
				ticker = ticker + a;
			} else {
				final Integer numero = new Random().nextInt(9);
				final String b = nums.get(numero);
				ticker = ticker + b;
			}
		}

		return ticker;
	}

	public static String tickerPosition(final Author author) {

		String ticker1 = "";
		ticker1 = ticker1 + author.getName().charAt(0);

		if (author.getMiddleName() == null || author.getMiddleName().isEmpty())
			ticker1 = ticker1 + "X";
		else
			ticker1 = ticker1 + author.getMiddleName().charAt(0);

		ticker1 = ticker1 + author.getSurname().charAt(0);

		final List<String> alpha = new ArrayList<String>();
		alpha.add("A");
		alpha.add("B");
		alpha.add("C");
		alpha.add("D");
		alpha.add("E");
		alpha.add("F");
		alpha.add("G");
		alpha.add("H");
		alpha.add("I");
		alpha.add("J");
		alpha.add("K");
		alpha.add("L");
		alpha.add("M");
		alpha.add("N");
		alpha.add("O");
		alpha.add("P");
		alpha.add("Q");
		alpha.add("R");
		alpha.add("S");
		alpha.add("T");
		alpha.add("U");
		alpha.add("V");
		alpha.add("W");
		alpha.add("X");
		alpha.add("Y");
		alpha.add("Z");

		final List<String> nums = new ArrayList<String>();
		nums.add("0");
		nums.add("1");
		nums.add("2");
		nums.add("3");
		nums.add("4");
		nums.add("5");
		nums.add("6");
		nums.add("7");
		nums.add("8");
		nums.add("9");

		String ticker2 = "";

		for (Integer i = 0; i < 4; i++) {
			final Integer selector = new Random().nextInt(26);
			if (selector > 14) {
				final Integer letra = new Random().nextInt(26);
				final String a = alpha.get(letra);
				ticker2 = ticker2 + a;
			} else {
				final Integer numero = new Random().nextInt(9);
				final String b = nums.get(numero);
				ticker2 = ticker2 + b;
			}
		}

		final String ticker = ticker1 + "-" + ticker2;

		return ticker;
	}
}
