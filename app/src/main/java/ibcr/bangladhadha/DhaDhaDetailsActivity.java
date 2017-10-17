package ibcr.bangladhadha;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mostafiz on 10/17/2017.
 */

public class DhaDhaDetailsActivity extends AppCompatActivity implements View.OnClickListener{


    String category_id;
    ArrayList<Dhadha> category_all_dhadhas;

    TextView current_dhadaTextView,answerTextView;
    Button showAnswerButton,next,prev;
    int current=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhadha_layout);
        category_id=getIntent().getStringExtra("Category");
        category_all_dhadhas=new ArrayList<>();
        for(int i=0;i<Globas.dhadhas.size();i++){
            Dhadha temp=Globas.dhadhas.get(i);
            if(temp.getCategory_id().equals(category_id)){
                category_all_dhadhas.add(temp);
            }
        }

        Log.e("DhadhaDetaiils","All: "+category_all_dhadhas);

        current_dhadaTextView=(TextView)findViewById(R.id.dhdhatextView);
        answerTextView=(TextView)findViewById(R.id.answer_textView);
        showAnswerButton=(Button)findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(this);
        next=(Button)findViewById(R.id.nextButton);
        next.setOnClickListener(this);
        prev=(Button)findViewById(R.id.prevButton);
        prev.setOnClickListener(this);

        setDhadha(current);

    }


    void setDhadha(int index){

        Dhadha dhadha=category_all_dhadhas.get(index);
        current_dhadaTextView.setText(dhadha.getDescr());
        answerTextView.setText(dhadha.getAnswer());
        answerTextView.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onClick(View view) {
        if(view==showAnswerButton){
            answerTextView.setVisibility(View.VISIBLE);
        }
        else if(view==next){
            current=current+1;
            setDhadha((current)%category_all_dhadhas.size());
        }
        else if(view==prev){
            current=current-1;
            setDhadha((current)%category_all_dhadhas.size());
        }
    }
}
