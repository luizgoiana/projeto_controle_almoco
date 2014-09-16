package controle.almoco.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;


public final class Util {
	public static final String	DATE_MASK	= "dd/MM/yyyy";

	/**
	 * Construtor padrão NAO permite instanciar
	 */
	private Util() {
	}

	/**
	 * Verifica se o texto informado à uma data
	 * 
	 * @param valor
	 *            O texto a ser verificado
	 * @return Se o valor informado à data ou não
	 * @see SimpleDateFormat
	 */
	public static boolean isDate(final String valor) {
		final SimpleDateFormat format = new SimpleDateFormat(DATE_MASK, Locale.getDefault());
		boolean retorno;
		format.setLenient(false);
		try {
			format.parse(valor);
			retorno = true;
		} catch (ParseException e) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Verifica se o texto informado à uma representação de Sim ou Não. Case insensitive
	 * 
	 * @param valor
	 *            O texto a ser verificado
	 * @return Se o valor é <code>"S"</code> ou <code>"N"</code>
	 */
	public static boolean isBoolean(final String valor) {
		boolean retorno = false;
		if ("S".equalsIgnoreCase(valor) || "N".equalsIgnoreCase(valor)) {
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Verifica se a data informada está entre os dia úteis da semana. Constantes da função GregorianCalendar.DAY_OF_WEEK 0 = Sunday, 1 = Monday, 2 = Tuesday, 3
	 * = Wednesday, 4 = Thursday, 5 = Friday, 6 = Saturday
	 * 
	 * @param data
	 *            A data a ser verificado
	 * @return true Se o valor for diferente <code>0</code> ou <code>6</code>
	 */
	public static boolean isDiaUtil(String data) throws ParseException {
		boolean retorno = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = sdf.parse(data);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataFormatada);
		int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
		diaDaSemana = diaDaSemana - 1;
		if (diaDaSemana == 0 || diaDaSemana == 6) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Verifica se o texto informado é um número
	 * 
	 * @param valor
	 *            O texto a ser verificado
	 * @return Se é número ou não
	 * @see Integer.parseInt
	 * @see Double.parseDouble
	 */
	public static boolean isNumber(final String valor) {
		boolean res = true;
		try {
			Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			res = false;
		}
		return res;
	}

	/**
	 * Converte uma data em String, formatando de acordo com o formato específico
	 * 
	 * @param date
	 *            A data a ser formatada
	 * @param format
	 *            O formato
	 * @return A data formatada
	 * @see SimpleDateFormat
	 */
	public static String dateToString(final Date date, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return date == null ? null : sdf.format(date);
	}

	/**
	 * Obtém uma data pelos dados informados de dia, mês e ano
	 * 
	 * @param dia
	 *            Dia
	 * @param mes
	 *            Mês
	 * @param ano
	 *            Ano
	 * @return Data resultante
	 */
	public static Date obterData(final int dia, final int mes, final int ano) {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, mes - 1);
		cal.set(Calendar.DAY_OF_MONTH, dia);

		return cal.getTime();
	}

	/**
	 * Obtém uma informação específica de uma data
	 * 
	 * @param data
	 *            Data informada
	 * @param campo
	 *            Informação da data desejada
	 * @return Valor do campo desejado
	 */
	public static int obterInfoData(final Date data, final int campo) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		return cal.get(campo);
	}

	/**
	 * Obtém a quantidade de dias entre duas datas
	 * 
	 * @param dataInicio
	 *            Data início de referência
	 * @param dataFim
	 *            Data fim de referência
	 * @return A diferença entre estas datas, em dias
	 */
	public static Integer daysBetween(final Date dataInicio, final Date dataFim) {
		Integer dias = 0;
		final Calendar inicio = new GregorianCalendar();
		inicio.setTime(dataInicio);
		final Calendar fim = new GregorianCalendar();
		inicio.setTime(dataFim);

		while (inicio.compareTo(fim) < 0) {
			inicio.add(Calendar.DAY_OF_MONTH, 1);
			dias++;
		}
		return dias;
	}

	public static String retiraMascaraCpfCnpj(final String valor) {
		String novaStringFmt = Normalizer.normalize(valor, Normalizer.Form.NFD);
		novaStringFmt = retiraString(valor);
		return novaStringFmt;
	}

	public static String retiraString(final String valor) {
		String retorno;
		if (null == valor) {
			retorno = null;
		} else {
			String novaStringFmt = Normalizer.normalize(valor, Normalizer.Form.NFD);
			novaStringFmt = valor.replaceAll("[^0-9]", "");
			retorno = novaStringFmt;
		}
		return retorno;
	}

	public static String obterMensagem(final String key, String params[]) {
		final FacesContext context = FacesContext.getCurrentInstance();

		final String text = getMessageResourceString(context.getApplication().getMessageBundle(), key, params, context.getViewRoot().getLocale());

		return text;
	}

	public static String getMessageResourceString(String bundleName, String key, Object params[], Locale locale) {
		String text = null;

		final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));

		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " not found ??";
		}
		if (params != null) {
			final MessageFormat msgFmt = new MessageFormat(text, locale);
			text = msgFmt.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		return Thread.currentThread().getContextClassLoader();
	}

	public static String formataMilhar(String valor) {

		if (valor == null || valor.isEmpty() || valor.equals("null")) {
			return null;
		}

		double d = 0;
		String S = valor;
		d = Double.valueOf(S).doubleValue();
		// formata o valor em reais
		NumberFormat nf = NumberFormat.getInstance();
		// converte um valor para String
		String valorFormatado = nf.format(d);
		int posicao = valorFormatado.indexOf(",");
		String fim = valorFormatado.substring(posicao + 1, valorFormatado.length());
		// acrescenta valor decimal caso não tenha
		if (posicao < 0) {

			// quando tiver uma casa decimal será acrescentado zero no final
		} else {
			for (int i = fim.length(); i < 2; i++) {
				valorFormatado += "0";
			}
		}

		return valorFormatado;
	}

	/**
	 * Metodo que retorna o valor monetário a partir do parâmetro que é passado pelo Oracle<br>
	 * Exemplo: <br>
	 * parâmetro de entrada: 500 -> saída: 500,00 <br>
	 * parâmetro de entrada: 500.1 -> saída: 500,10 <br>
	 * parâmetro de entrada: 500000000.11 -> saída: 500.000.000,11 <br>
	 * .
	 * 
	 * Utilizar o formata moeda da Classe Util/Formatadores Formatadores.formataMoeda(BigDecimal.valueOf(Double.valueOf(Util.formataNumero(valor))).setScale(2,
	 * BigDecimal.ROUND_HALF_EVEN));
	 */
	@Deprecated
	public static String formataMoeda(String valor) {

		if (valor == null || valor.isEmpty() || valor.equals("null")) {
			return null;
		}

		double d = 0;
		String S = valor;
		d = Double.valueOf(S).doubleValue();
		// formata o valor em reais
		NumberFormat nf = NumberFormat.getInstance();
		// converte um valor para String
		String valorFormatado = nf.format(d);
		int posicao = valorFormatado.indexOf(",");
		String fim = valorFormatado.substring(posicao + 1, valorFormatado.length());
		// acrescenta valor decimal caso não tenha
		if (posicao < 0) {
			valorFormatado += ",00";
			// quando tiver uma casa decimal será acrescentado zero no final
		} else {
			for (int i = fim.length(); i < 2; i++) {
				valorFormatado += "0";
			}
		}

		return valorFormatado;

	}

	public static String formatarDataParaPtBR(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(data);
	}

	public static String formatarDataParaMesAno(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		return formatter.format(data);
	}

	public static String formatarDataParaAno(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(data);
	}

	public static String formatarDataParaDiaMesAnoHoraMinuto(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
		return formatter.format(data);
	}

	public static String formarNumeroAdicionandoZero(int numero) {
		String num = numero + "";
		if (num.length() == 1)
			num = "0" + num;

		return num;
	}

	public static String formarNumeroAdicionandoZero(long numero) {
		String num = numero + "";
		if (num.length() == 1)
			num = "0" + num;

		return num;
	}

	/**
	 * Obtém uma string e retorna uma data
	 * 
	 * @param dia
	 *            Dia
	 * @param mes
	 *            Mês
	 * @param ano
	 *            Ano
	 * @return Data resultante
	 */
	public static Date stringToDate(String valor) throws ParseException {
		String formato = "dd/MM/yyyy";
		Date date = new SimpleDateFormat(formato).parse(valor);
		return date;
	}

	/**
	 * Método adiconar/subtrair quantidade de mes(es) de uma data informada
	 * 
	 * @param date
	 * @param qtd
	 * @return
	 */
	public static Date addSubtrairMesData(Date date, int qtd) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +qtd);

		return calendar.getTime();

	}

	/**
	 * Método adiconar/subtrair quantidade de dia(s) de uma data informada
	 * @param date
	 * @param qtd
	 * @return
	 */
	public static Date addSubtrairDiaData(Date date, int qtd) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +qtd);

		return calendar.getTime();

	}

	public static int getDiaSemana(Date data) {
		Calendar agora = Calendar.getInstance();
		agora.setTime(data);
		return agora.get(Calendar.DAY_OF_WEEK);
	}

	public static boolean isFinalSemana(Date data) {
		int dayWeek = getDiaSemana(data);
		return dayWeek == Calendar.SUNDAY || dayWeek == Calendar.SATURDAY;
	}

	public static Date adquirirDataAtualSemHora() {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			return (Date) formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date removerTime(Date data) {
		try {
			return stringToDate(formatarDataParaPtBR(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean validaString(String arg) {
		return arg != null && !"".equals(arg);
	}
	
	public static boolean validaObjeto(Object arg){
		return arg != null && !"".equals(arg);
	}

	public static boolean validaNumber(Number numero) {
		return numero != null && numero.doubleValue() != 0.0;
	}

	public static boolean validaArray(Object[] array) {
		return array != null && array.length > 0;
	}

	public static boolean validaColecao(Collection<?> colecao) {
		return colecao != null && !colecao.isEmpty();
	}

	public static String formataDuasCasas(Double valor) {
		DecimalFormat df2 = new DecimalFormat("#0.00");
		return formata(valor, df2);
	}

	public static String formata(Number valor, String formato) {
		DecimalFormat df = new DecimalFormat(formato);
		return formata(valor, df);
	}

	public static double converterDoubleDoisDecimais(double precoDouble) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(precoDouble);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		double preco = Double.parseDouble(string2);
		return preco;
	}

	private static String formata(Number valor, DecimalFormat df) {
		try {
			return df.format(valor);
		} catch (Exception e) {
			return df.format(0);
		}
	}

	public static boolean isMesAtual(Date data) {
		Calendar dtAtual = Calendar.getInstance();
		Calendar dtParam = Calendar.getInstance();
		dtParam.setTime(data);
		return dtAtual.get(Calendar.YEAR) == dtParam.get(Calendar.YEAR) && dtAtual.get(Calendar.MONTH) == dtParam.get(Calendar.MONTH);
	}

	public static class HTTPResponse {

		/**
		 * Botar a resposta do servidor como download
		 * 
		 * @param Nome
		 *            do arquivo com extensão de como vai ser apresentado o download para o Client
		 * @param Arquivo
		 *            para Download
		 */
		public static void downloadFile(String fileNameDownload, File f) throws IOException {
			downloadFile(fileNameDownload, new FileInputStream(f), f.length());
		}

		/**
		 * Botar a resposta do servidor como download
		 * 
		 * @param Nome
		 *            do arquivo com extensão de como vai ser apresentado o download para o Client
		 * @param Arquivo
		 *            para Download
		 */
		public static void downloadFile(String fileNameDownload, InputStream is, long size) throws IOException {
			byte[] bbuf = new byte[(int) size];
			DataInputStream din = new DataInputStream(is);
			int length = 0;

			HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			while ((din != null) && ((length = din.read(bbuf)) != -1)) {
				res.getOutputStream().write(bbuf, 0, length);
			}
			is.close();

			res.setContentType("application/download");

			res.setHeader("Content-disposition", String.format("%s%s%s", "attachment;filename=\"", fileNameDownload, "\""));

			res.getCharacterEncoding();
			FacesContext.getCurrentInstance().responseComplete();
		}

	}

	/**
	 * Obtém os anos entre duas datas
	 * 
	 * @param dataInicio
	 *            Data início de referência
	 * @param dataFim
	 *            Data fim de referência
	 * @return Os anos entre estas datas
	 */
	public static List<Integer> yearsBetween(int anoInicio, int anoFim) {
		List<Integer> anos = new ArrayList<Integer>();

		while (anoInicio <= anoFim) {
			int ano = anoInicio;
			anos.add(ano);
			anoInicio++;
		}
		return anos;
	}

	/**
	 * Verifica se o texto informado é um CNPJ
	 * 
	 * @param cnpj
	 *            O CNPJ a ser verificado
	 * @return Se é CNPJ ou não
	 */
	public static boolean isCnpj(final String cnpj) {
		boolean retorno = true;
		if ((cnpj == null) || (cnpj.length() != 14)) {
			retorno = false;
		}
		return retorno;
	}

	public static String formatarCpfCnpj(String cpfCnpj) {
		JFormattedTextField cnpj_cpf = new JFormattedTextField();

		String valor = cpfCnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
		if (valor.length() == 11) {
			try {
				cnpj_cpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				cnpj_cpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
		cnpj_cpf.setText(valor);

		return cnpj_cpf.getText();

	}

	public static Date ajustaHora(Date dtSemHora) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtSemHora);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * Método para retirar zero de valores porcetagem (depois do ponto)
	 * 
	 * @param numero
	 * @return
	 */
	public static double converterDoubleSemDecimais(double numeroDouble) {
		DecimalFormat fmt = new DecimalFormat("0.0");
		String string = fmt.format(numeroDouble);
		String[] part = string.split("[.]");
		String string2 = part[0];
		double preco = Double.parseDouble(string2);
		return preco;
	}

	public static String limparCpfCnpj(String cpfCnpj) {
		return cpfCnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
	}

	/**
	 * Formatar data retirando o time
	 * 
	 * @param data
	 * @return Date
	 */
	public static Date dateRemoveTime(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualFormatadaStr = formatter.format(data);

		Date dataFormatada = null;
		try {
			dataFormatada = formatter.parse(dataAtualFormatadaStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataFormatada;
	}

	public static String formataNumero(String numero) {
		if (isNumber(numero))
			return numero;
		return numero.replace(".", "").replace(",", ".");
	}

	public static int getAno(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Retorna o ultimo dia do mês
	 * 
	 * @param data
	 * @return data
	 * @throws ParseException
	 */
	public static Date ultimoDiaMes(Date data) throws ParseException {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(data);

		int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = (cal.get(Calendar.MONDAY) + 1);
		int ano = cal.get(Calendar.YEAR);

		data = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes + "/" + ano);

		return data;

	}
	
	public static Date parseDatePtBr(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {			
			
		}
		
		return date;
	}
	
}