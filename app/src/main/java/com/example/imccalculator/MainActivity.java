package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    RadioButton homme , femme ;
    TextView resultat , interpretation;
    EditText poids , taille, age ;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homme =findViewById(R.id.rdbHomme);
        femme =findViewById(R.id.rdbFemme);
        resultat=findViewById(R.id.lblResultat);
        interpretation=findViewById(R.id.lblInterpretation);
        poids=findViewById(R.id.txtPoids);
        taille=findViewById(R.id.txtTaille);
        age=findViewById(R.id.txtAge);
        calculate=findViewById(R.id.btnCalculIMG);
        int sexetype =sexe();
        if (sexetype==-1){
            Toast.makeText(getApplicationContext(),"choose only one option ",Toast.LENGTH_LONG).show();
            homme.setChecked(false);
            femme.setChecked(false);
        }

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double resultatval ;
                DecimalFormat df = new DecimalFormat("0.00");
                int sexeval = sexe();

                Integer ageval=Integer.parseInt(age.getText().toString());
                Double poidsval=Double.parseDouble(poids.getText().toString());
                Double tailleval=Double.parseDouble(taille.getText().toString());
                if (ageval<16){
                    resultatval=ImgEnfant(poidsval,tailleval,ageval,sexeval);
                    resultat.setText("votre IMG est "+df.format(resultatval).toString()+"%");
                    interpretation.setText(Interpretation(resultatval,sexeval));
                     }else resultatval=ImgAdulte(poidsval,tailleval,ageval,sexeval);
                resultat.setText("votre IMG est "+df.format(resultatval).toString()+"%");
                interpretation.setText(Interpretation(resultatval,sexeval));


            }


        });






    }
    public Double Imc(Double poids,Double taille){
        Double tailleenmetre = taille/100;
        return poids/(tailleenmetre*tailleenmetre);
    }
    public Double ImgAdulte(Double poids,Double taille ,int age ,int sexe){
        Double a = Imc(poids,taille);
        return (Double) ((1.20*a)+(0.23*age)-(10.8*sexe)-5.4);
    }
    public Double ImgEnfant(Double poids,Double taille ,int age ,int sexe){
        Double a = Imc(poids,taille);
        return (Double) ((1.51*a)+(0.70*age)-(3.6*sexe)+1.4);
    }
    public int sexe(){
        if (homme.isChecked())
            return 1;
            else if (femme.isChecked())
                return 0;
            else return -1;
    }
    public String Interpretation(Double img,int sexe){
        if (sexe==0){
            if (img <25) return "TRop maigre" ;
            else if (img >25 && img <30)  return  "pourcentage normale";
            else return "trop de graisse";


        } else if (sexe==1) {
            if (img <15) return "TRop maigre" ;
            else if (img >15 && img <20)  return  "pourcentage normale";
            else return "trop de graisse";


        }
        else return "error";
    }

}