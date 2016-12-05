package com.iut.german_marabel.icongame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        String score = "0";


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getString("score");
        }

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edt = SP.edit();

        String strUserName = SP.getString("username","");


        TextView top = (TextView)findViewById(R.id.displayNewRecord);
        if (Integer.parseInt(score) > SP.getInt("highScore", 0)){
            top.setText("Bravo " + strUserName + " ! Nouveau reccord !");
            edt.putInt("highScore",  Integer.parseInt(score));
            edt.commit();
        }
        else{
            top.setText("Pas mal " + strUserName + "... Mais il va falloir s'entrainer !");
        }

        TextView tscore = (TextView)findViewById(R.id.Score);
        tscore.setText(Integer.parseInt(score) + " Points !");

        TextView savoirInutile = (TextView)findViewById(R.id.uselessMessage);
        Random r = new Random();
        int i = r.nextInt(64);
        switch (i){
            case 1 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La racine carrée de corde est ficelle.");
                break;
            case 2 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Bien que le sous-marin surpasse le bateau à quasiment tous points de vue, ce dernier représente plus de 97 % des voyages transaquatiques.");
                break;
            case 3 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les téléphones portables ne donnent pas le cancer. Seulement l'hépatite.");
                break;
            case 4 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le pantalon fut inventé au XVIe siècle pour éviter la colère de Poséidon. En effet, la vue de marins nus donne de l'urticaire au dieu de la mer.");
                break;
            case 5 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La masse atomique du germanium est de 72,64.");
                break;
            case 6 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "89 % des tours de magie relèvent non de la magie, mais de la sorcellerie.");
                break;
            case 7 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "L'œil d'une autruche est plus gros que son cerveau.");
                break;
            case 8 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Dans la mythologie grecque, Dédale créa des ailes pour faire bisquer un groupe de minotaures.");
                break;
            case 9 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les humains peuvent survivre sous l'eau. Mais pas très longtemps.");
                break;
            case 10 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Reshep, dieu de la guerre cananéen, avait une gazelle qui lui poussait sur le front.");
                break;
            case 11 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le pluriel de cheval est chevaux. Le pluriel de portail est un vin liquoreux de la péninsule ibérique.");
                break;
            case 12 :
                savoirInutile.setText("Le saviez-vous ?\n\n " +"Les ARN polymérases 1 permettent la synthèse de longs ARN ribosomiques.");
                break;
            case 13 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les rats sont incapables de vomir.");
                break;
            case 14 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Un iguane peut rester sous l'eau pendant 28,7 minutes.");
                break;
            case 15 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le ver solitaire peut atteindre 23 m dans le corps humain.");
                break;
            case 16 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le paradoxe du chat de Schrödinger postule une situation dans laquelle un chat prisonnier à l'intérieur d'une boîte est à la fois mort et vivant. Schrödinger s'est servi de ce paradoxe pour pouvoir tuer des chats impunément.");
                break;
            case 17 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Chaque centimètre carré de peau humaine comporte en moyenne 5 millions de bactéries.");
                break;
            case 18 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le Soleil est 330 330 fois plus grand que la Terre.");
                break;
            case 19 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "L'espérance de vie moyenne d'un rhinocéros en captivité est de 15 ans.");
                break;
            case 20 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les vulcanologues ont tous les oreilles pointues.");
                break;
            case 21 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les avocats sont les plus riches de tous les fruits.");
                break;
            case 22 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les avocats sont les plus riches de tous les fruits et de toutes les professions libérales.");
                break;
            case 23 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La révolution de la Lune autour de la Terre dure 27,32 jours.");
                break;
            case 24 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La milliardième décimale de Pi est 9.");
                break;
            case 25 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Si vous avez du mal en calcul mental, il y a un moyen mnémotechnique tout simple : un plus deux plus 60 plus 12 moins six billions n'est pas égal à 504. Voilà. Vous pouvez maintenant effectuer facilement des opérations arithmétiques complexes de tête.");
                break;
            case 26 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Un gallon d'eau pèse 3,78 kilos.");
                break;
            case 27 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "L'eau chaude gèle plus vite que l'eau froide.");
                break;
            case 28 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le miel ne se gâte pas.");
                break;
            case 29 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "A l'âge adulte, le corps humain contient 250 grammes de sel.");
                break;
            case 30 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Une nanoseconde dure un milliardième de seconde.");
                break;
            case 31 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Selon la légende germanique, le chariot céleste de Thor était tiré par deux boucs.");
                break;
            case 32 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La Chine est le deuxième pays producteur de soja au monde.");
                break;
            case 33 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Avec 3410 °C, le tungstène détient le record de température de fusion de tous les métaux.");
                break;
            case 34 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "En cas de mauvaise haleine, il est conseillé de se brosser doucement la langue deux fois par jour.");
                break;
            case 35 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le dentifrice romain était composé d'urine humaine. L'utilisation de cet ingrédient a perduré jusqu'au XVIIIe siècle.");
                break;
            case 36 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Signé par Henri IV, l'édit de Nantes de 1598 reconnaît la liberté de culte aux protestants. Il est révoqué moins d'un siècle plus tard, en 1685.");
                break;
            case 37 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Pi est le rapport entre la circonférence d'un cercle et son diamètre en géométrie euclidienne.");
                break;
            case 38 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La guerre américano-mexicaine a pris fin en 1848 avec la signature du traité de Guadalupe Hidalgo.");
                break;
            case 39 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "C'est le Canadien Sandford Fleming qui, en 1879, a proposé le premier de diviser le monde en fuseaux horaires normalisés.");
                break;
            case 40 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Marie Curie a découvert la théorie de la radioactivité, le traitement de la radioactivité et la mort par radioactivité.");
                break;
            case 41 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "À la fin de La Mouette d'Anton Tchekhov, Konstantin se suicide.");
                break;
            case 42 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Contrairement aux idées reçues, les Inuits n'ont pas une centaine de mots pour dire « neige ». Ils ont en revanche 234 façons de dire « caramel ».");
                break;
            case 43 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Dans l'Angleterre victorienne, un roturier n'avait pas le droit de regarder la reine en face. En effet, on pensait à l'époque que les pauvres étaient capables de voler les pensées. Aujourd'hui, la science a prouvé que moins de 4 % des pauvres possèdent cette faculté");
                break;
            case 44 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Quand en 1862, Abraham Lincoln ratifia la Proclamation d'émancipation qui accordait la liberté aux esclaves, il était en état de somnambulisme et ne se souvenait de rien le matin suivant.");
                break;
            case 45 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "En 1983, à la demande d'un enfant mourant, le grand cycliste Louison Bobet a mangé 75 galettes de blé noir avant de décéder d'une intoxication au blé noir.");
                break;
            case 46 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "William Shakespeare n'a jamais existé. Ses pièces ont été créées en 1589 par Francis Bacon, qui s'est servi d'une planche de ouija pour asservir des esprits dramaturges.");
                break;
            case 47 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "On affirme à tort que Thomas Edison a inventé le culturisme en 1878. En réalité, Nikola Tesla avait fait breveter cette activité trois ans plus tôt, sous le nom de « bobinisme ».");
                break;
            case 48 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Les baleines sont deux fois plus intelligentes et trois fois plus goûteuses que les humains.");
                break;
            case 49 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Il a fallu attendre 1895 pour que le frein automobile soit inventé. Jusque-là, le conducteur devait rester en permanence dans le véhicule et tourner en rond en attendant le retour de ses passagers.");
                break;
            case 50 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Edmund Hillary, premier homme à avoir gravi l'Everest, accomplit cet exploit par hasard en chassant un oiseau.");
                break;
            case 51 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le carbone soumis à une pression intense produit du diamant. Le diamant soumis à une pression intense produit les particules en polystyrène servant à caler les colis.");
                break;
            case 52 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le poisson le plus venimeux est l'hoplostèthe orange. Son corps entier, à l'exception des yeux, est constitué d'un poison mortel. Ses yeux sont composés d'un autre poison mortel moins violent.");
                break;
            case 53 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le poste de bouffon fut inventé par accident, quand un vassal en pleine crise d'épilepsie déclencha l'hilarité générale.");
                break;
            case 54 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La comète de Halley est visible depuis la Terre tous les 76 ans. Les 75 autres années, elle hiberne tranquillement dans le noyau du Soleil.");
                break;
            case 55 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Le premier vol commercial a eu lieu en 1914. Tous les passagers ont hurlé du décollage à l'atterrissage.");
                break;
            case 56 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Dans la mythologie grecque, Prométhée a volé le feu aux dieux pour le donner aux hommes, mais il a gardé les bijoux pour lui.");
                break;
            case 57 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "La personne qui a découvert que le lait est potable avait particulièrement soif.");
                break;
            case 58 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Avant l'invention de l'avion, pour voler, il fallait ingérer de grandes quantités d'hélium.");
                break;
            case 59 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Avant l'invention des œufs brouillés en 1912, le brunch traditionnel était constitué de poussins crus ou de cailloux brouillés.");
                break;
            case 60 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Dans les années 30, il était interdit d'avoir un lapin de compagnie. D'où une recrudescence de souris avec de fausses oreilles de lapin collées sur la tête.");
                break;
            case 61 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Un enfant sur six sera un jour ou l'autre kidnappé par un Néerlandais.");
                break;
            case 62 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Selon des algorithmes très évolués, le nom le plus classe du monde est Abitbol.");
                break;
            case 63 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "Pour fabriquer une photocopieuse, il suffit de photocopier un miroir.");
                break;
            case 0 :
                savoirInutile.setText("Le saviez-vous ?\n\n " + "C'est par les rêves que notre subconscient nous rappelle d'aller à l'école en slip et de faire tomber nos dents.");
                break;
            default:
                savoirInutile.setText("Le saviez-vous ?\n\n " + "C'est par les rêves que notre subconscient nous rappelle d'aller à l'école en slip et de faire tomber nos dents.");

        }


        //-----
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        Score bddScore = realm.createObject(Score.class);
        bddScore.setScore(Integer.parseInt(score));

        realm.copyToRealmOrUpdate(bddScore);
        //realm.createOrUpdate(bddScore);

        RealmResults<Score> result = realm.where(Score.class)
                .findAll();

        Log.d(null, "Realm result");
        for(Score sc : result)
        {
            Log.d(null, "=" + sc);
        }

        realm.commitTransaction();
        realm.close();
        //-----

        final Button playAgain = (Button) findViewById(R.id.playAgain);
        final Button home = (Button) findViewById(R.id.backHome);

        playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FinishActivity.this, GameActivity.class);
                FinishActivity.this.startActivity(myIntent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FinishActivity.this, MenuActivity.class);
                FinishActivity.this.startActivity(myIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {}

}
