package alvi17.bangladhadha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

/**
 * Created by Mostafiz on 10/17/2017.
 */

public class DhaDhaDetailsActivity extends AppCompatActivity implements View.OnClickListener{


    String category_id;
    ArrayList<Dhadha> category_all_dhadhas;

    TextView current_dhadaTextView,answerTextView;
    Button showAnswerButton;
    ImageButton next,prev;
    int current=0;
    TextView category_headertextview;
    String category;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhadha_layout);
        category_id=getIntent().getStringExtra("Category");
        category=getIntent().getStringExtra("Category_title");
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
        next=(ImageButton)findViewById(R.id.nextButton);
        next.setOnClickListener(this);
        prev=(ImageButton)findViewById(R.id.prevButton);
        prev.setOnClickListener(this);

        category_headertextview=(TextView)findViewById(R.id.dhadha_headerTextVIew);


        setDhadha(current);

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("0754C239B1E2E19421FDE46BCEFB8855")
                .build();
        adView.loadAd(adRequest);

    }

    private InterstitialAd interstitial;
    void setDhadha(int index){

        Dhadha dhadha=category_all_dhadhas.get(index);
        current_dhadaTextView.setText(dhadha.getDescr());
        answerTextView.setText(dhadha.getAnswer());
        category_headertextview.setText(category+"ঃ "+getBanglaumber(index+1));
        answerTextView.setVisibility(View.INVISIBLE);
        showAnswerButton.setVisibility(View.VISIBLE);

        if(index%5==0){
            interstitial=new InterstitialAd(this);
            interstitial.setAdUnitId("ca-app-pub-6508526601344465/4556839375");
            AdRequest aRequest = new AdRequest.Builder().addTestDevice("0754C239B1E2E19421FDE46BCEFB8855").build();

            // Begin loading your interstitial.
            interstitial.loadAd(aRequest);

            interstitial.setAdListener(
                    new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            interstitial.show();
                        }
                    }
            );
        }
    }

    String getBanglaumber(int index){

        String  temp=index+"";
        String s="";
        for(int i=0;i<temp.length();i++){
            if(temp.charAt(i)=='1'){
                s+='১';
            }
            else if(temp.charAt(i)=='2'){
                s+='২';
            }
            else if(temp.charAt(i)=='3'){
                s+='৩';
            }
            else if(temp.charAt(i)=='4'){
                s+='৪';
            }
            else if(temp.charAt(i)=='5'){
                s+='৫';
            }
            else if(temp.charAt(i)=='6'){
                s+='৬';
            }
            else if(temp.charAt(i)=='7'){
                s+='৭';
            }
            else if(temp.charAt(i)=='8'){
                s+='৮';
            }
            else if(temp.charAt(i)=='9'){
                s+='৯';
            }
            else if(temp.charAt(i)=='0'){
                s+='০';
            }
        }

        return s;
    }


    @Override
    public void onClick(View view) {
        if(view==showAnswerButton){
            answerTextView.setVisibility(View.VISIBLE);
            showAnswerButton.setVisibility(View.INVISIBLE);
        }
        else if(view==next){
            current=current+1;
            setDhadha((current)%category_all_dhadhas.size());
        }
        else if(view==prev){
            current=current-1;
            if(current<0){
                current=category_all_dhadhas.size()-1;
            }
            setDhadha((current)%category_all_dhadhas.size());
        }
    }
}
