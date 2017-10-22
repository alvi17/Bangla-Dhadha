package alvi17.bangladhadha;

/**
 * Created by Mostafiz on 10/17/2017.
 */

public class Dhadha {

    String id,category_id,descr,answer;

    public Dhadha(String id,String category_id,String descr,String answer){
        this.id=id;
        this.category_id=category_id;
        this.descr=descr;
        this.answer=answer;
    }

    public String getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getDescr() {
        return descr;
    }
}
