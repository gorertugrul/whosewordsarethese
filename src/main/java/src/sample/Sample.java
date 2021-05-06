package src.sample;

import src.model.Text;
import src.model.Writer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample {
    public static List<Writer> generateSample() {
        Writer writer1 = new Writer();
        writer1.setName("Yazar1");
        Text text1 = new Text();
        text1.setWriter(writer1.getName());
        text1.setSubject("Konu1");
        text1.setContent("Bir şairin önemi, ne hakkında yazılan yazıların, yapılan incelemelerin sayısıyla ne de" +
                " şiirlerinin herkesçe bilinmesiyle ölçülür. Bunlar yanıltıcı olabilir. Bence asıl önemli olan, bir başka şairden " +
                "söz edilirken ya da şiir üzerine bir yazı yazılırken onun adının hangi sıklıkla kullanıldığıdır." +
                " Bu durumda, hakkında yazı yazılan şair, onunla ölçülüyor demektir. Şairin önemini, bulunduğu yeri, işte bu belirler.");
        Text text2 = new Text();
        text2.setWriter(writer1.getName());
        text2.setSubject("Konu2");
        text2.setContent("Kaleminize; günümüz tabiriyle klavyenize güveniyorsunuz ama konu üretmek konusunda yetersiz mi kalıyorsunuz? " +
                "Gelir kaynağınız olan bloğunuzda veya içerik ürettiğiniz dergi, gazete, web sitesinde yazacak konu bulmakta zorlanıyor musunuz?" +
                " Aslında yazacak konu bulabilsem günde bir yazı yerine üç-dört yazı yazabilirim diyorsanız hızlanmanın zamanı gelmiş demektir.");

        writer1.addWritings(text1);
        writer1.addWritings(text2);


        Writer writer2 = new Writer();
        writer2.setName("Yazar2");
        Text text3 = new Text();
        text3.setWriter(writer2.getName());
        text3.setSubject("Konu3");
        text3.setContent("Modelleme ve Simülasyon (MODSİM) teknolojilerinin önemli uygulama alanlarından biri de" +
                " üretim sistemlerinin gerçeğe yakın bir şekilde modellenerek, yöneylem araştırması kapsamında kullanılmasıdır." +
                " Bu çalışmada, metal veya kompozit malzemelerden oluşan bir üretim sistemine yönelik geliştirilen MODSİM yaklaşımı" +
                " ile fabrika üretim hattında malzeme çıktı sayısının artırılması," +
                " işçilerin davranış ve çalışma sürelerine yönelik elde edilen analiz sonuçlarından bahsedilecektir. ");

        writer2.addWritings(text3);
        return Stream.of(writer1, writer2).collect(Collectors.toList());
    }
}
