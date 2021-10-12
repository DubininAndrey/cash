import java.io.IOException;
import java.text.DecimalFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
  public static void main(String[] args) {
    try {
      Document doc = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp").get();
      Elements valute = doc.getElementsByTag("Valute");

      Element norwayKrone = valute.get(18);
      Elements elementsFromKrone = norwayKrone.children();
      String kroneNominal = elementsFromKrone.get(2).text();
      double kroneNominalDouble = Double.parseDouble(kroneNominal);
      String krone = elementsFromKrone.get(4).text();
      String kroneDouble = krone.replace(",", ".");
      double kroneCrownInRub = Double.parseDouble(kroneDouble) / kroneNominalDouble;

      Element hungarianForint = valute.get(7);
      Elements elementsFromForint = hungarianForint.children();
      String forintNominal = elementsFromForint.get(2).text();
      double forintNominalDouble = Double.parseDouble(forintNominal);
      String forint = elementsFromForint.get(4).text();
      String forintDouble = forint.replace(",", ".");
      double forintInRub = Double.parseDouble(forintDouble) / forintNominalDouble;

      double kroneToForint = kroneCrownInRub / forintInRub;
      String formattedDouble = new DecimalFormat("0.00").format(kroneToForint);

      System.out.println("Cтоимость одной норвежской кроны " + formattedDouble
          + " в венгерских форинтах.");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
