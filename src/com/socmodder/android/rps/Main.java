package com.socmodder.android.rps;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.socmodder.android.rps.Database.DatabaseHelper;
import com.socmodder.android.rps.Database.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Main extends OrmLiteBaseActivity<DatabaseHelper> {
    ImageButton rock, paper, scissors;
    String response;
    TextView textView;
    int start, end;
    ListView listview;
    RuntimeExceptionDao<Game, Integer> gameDao;


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.rock_button:
                    response = getResponse("rock");
                    break;
                case R.id.paper_button:
                    response = getResponse("paper");
                    break;
                case R.id.scissor_button:
                    response = getResponse("scissors");
                    break;
            }
            start = response.indexOf("<h2>");
            end = response.indexOf("</h2>");
            textView.setText(response.substring(start+4, end));
            Game game = new Game(response.substring(start+4, end));
            gameDao.create(game);
            doDatabaseStuff();
        }
    };
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rock = (ImageButton)findViewById(R.id.rock_button);
        paper = (ImageButton)findViewById(R.id.paper_button);
        scissors = (ImageButton)findViewById(R.id.scissor_button);
        textView = (TextView)findViewById(R.id.main_textview);
        listview = (ListView)findViewById(R.id.listview);
        doDatabaseStuff();

        rock.setOnClickListener(onClickListener);
        paper.setOnClickListener(onClickListener);
        scissors.setOnClickListener(onClickListener);
    }

    public String getResponse(String request){
        String response = null;
        SubmitThrowTask submitThrowTask = new SubmitThrowTask();
        try {
            response = submitThrowTask.execute(request).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void doDatabaseStuff(){
        ArrayAdapter<String> adapter = null;
        gameDao = getHelper().getGameDao();

        List<Game> list = gameDao.queryForAll();
        ArrayList<String> gameList = new ArrayList<String>();
        for(int i=0; i<list.size(); i++){
            gameList.add(list.get(i).getResult());
        }
        adapter = new ArrayAdapter<String>(Main.this, android.R.layout.simple_list_item_1, gameList);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);
    }
}
