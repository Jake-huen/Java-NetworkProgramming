package URL_URI;

public class QueryStringUsage {

    public static void main(String[] args){
        QueryString qs = new QueryString();
        qs.add("hl","en");
        qs.add("as_q","Java");
        qs.add("as_eqq","I/O");
        String url = "https://www.google.com/search?"+qs;
        System.out.println(url);
    }
}
