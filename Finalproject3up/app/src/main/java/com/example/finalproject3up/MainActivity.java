package com.example.finalproject3up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // 目標單字
    private static final String TARGET_WORD = "duck";
    private String[] words = {"dream", "blossom", "whisper", "rhythm", "cascade", "mystical", "serenity", "jubilant", "luminescent", "quintessential",
            "ethereal", "enchanting", "sapphire", "sunset", "glisten", "harmony", "talisman", "ephemeral", "whimsical", "twilight",
            "tranquil", "labyrinth", "azure", "silhouette", "inspire", "effervescent", "sonorous", "radiant", "nostalgia", "lullaby",
            "ineffable", "melody", "serendipity", "melancholy", "ephemeral", "cynosure", "resplendent", "evanescent", "diaphanous", "lucent",
            "umbra", "iridescence", "synergy", "petrichor", "bucolic", "apple", "banana", "luminary", "oblivion", "quixotic",
            "splendid", "ebb", "flow", "sojourn", "whirligig", "sibylline", "ephemeral", "halcyon", "dalliance", "efflorescence",
            "gossamer", "threnody", "susurrus", "lilt", "paragon", "wanderlust", "ephemeral", "hiraeth", "luminescence", "mellifluous",
            "obfuscate", "plethora", "quintessence", "radiance", "sonorous", "tranquility", "ubiquitous", "verdant", "whimsy", "xylophone",
            "yonder", "zeppelin", "seraphic", "sky", "languor", "ephemeral", "bucolic", "epiphany", "halcyon", "labyrinth",
            "mellifluous", "quasar", "susurrant", "threnody", "vacillate", "wonderment", "xanadu", "fire", "zephyr"};

    private String[][] wordGroups = {
            {"Apple", "Banana", "Orange", "Grapes", "Strawberry", "Pineapple", "Watermelon", "Mango", "Cherry", "Blueberry"},
            {"Laptop", "Mountain", "Elephant", "Guitar", "Sunflower", "Rainbow", "Coffee", "Bookshelf", "Butterfly", "Ocean"},
            {"Sky", "Cactus", "Piano", "Rose", "Chocolate", "Raindrop", "Telescope", "Fireworks", "Dragonfly", "Desert"},
            {"Television", "Snowflake", "Maple", "Horse", "Popcorn", "Waterfall", "Lighthouse", "Whale", "Jellyfish", "Safari"},
            {"Robot", "Tornado", "Cupcake", "Astronaut", "Thunder", "Bamboo", "Galaxy", "Tiger", "Seashell", "Moonlight"},
            {"Computer", "Volcano", "Unicorn", "Bicycle", "Chameleon", "Fog", "Island", "Penguin", "Sunglasses", "Waterfall"},
            {"Satellite", "Acorn", "Origami", "Toucan", "Chandelier", "Puzzle", "Harmony", "Paradox", "Sculpture", "Voyage"},
            {"Rainforest", "Saturn", "Tangerine", "Magnet", "Mist", "Infinity", "Harvest", "Horizon", "Breeze", "Vortex"},
            {"Raspberry", "Galaxy", "Peacock", "Candlelight", "Harmonica", "Quasar", "Eclipse", "Lunar", "Avocado", "Zephyr"},
            {"Pluto", "Riverside", "Crystal", "Cherub", "Ethereal", "Lunar", "Pegasus", "Meadow", "Aquarium", "Caramel"}
    };


    private EditText meditTextInput;
    private TextView mtextViewQuestion;
    private TextView mtextViewNumber;
    private TextView mtextViewTimer;
    private TextView mtextViewLevelName;
    private int mnumber = 1;
    private int levelNumber;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable runnable;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        meditTextInput = findViewById(R.id.editTextTextPersonName);
        mtextViewQuestion = findViewById(R.id.question);
        mtextViewNumber = findViewById(R.id.number);
        mtextViewTimer = findViewById(R.id.timer);
        mtextViewLevelName = findViewById(R.id.levelname);
        meditTextInput.setInputType(InputType.TYPE_CLASS_TEXT);
        ImageView imageView = findViewById(R.id.imageView2);

        //音樂
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.gamemusic_u);
        mediaPlayer.setLooping(true);

        // 設定點擊事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic(); // 切換音樂播放狀態
            }
        });
        // 你也可以在其他地方使用loadDictionary方法來讀取字典
        Map<String, String> loadedDictionary = DictionaryManager.loadDictionary(this);

        // 使用讀取的字典進行其他操作
        String chineseTranslation = loadedDictionary.get("dream");
        Log.d("Translation", "The Chinese translation of 'dream' is: " + chineseTranslation);

        // 獲取啟動該 Activity 的 Intent
        Intent intent = getIntent();

        String source = intent.getStringExtra("source");
        if (source != null) {
            if (source.equals("MainActivity3")) {
                Log.d("source", source);
                // 這個 Intent 是從 MainActivity3 啟動的
                // 獲取傳遞的關卡數字，預設值為-1（可以根據你的需求設置一個合適的預設值）
                levelNumber = intent.getIntExtra("levelNumber", -1);
                String levelName = intent.getStringExtra("levelName");

                Log.d("levelNumber", String.valueOf(levelNumber));


                // 在這裡可以使用獲取到的關卡數字進行相應的操作，例如顯示在 TextView 中
                mtextViewLevelName.setText(levelName);

                startTimer();
                // 在這裡對單字數組進行打亂
                shuffleAllWordGroups();

                mtextViewQuestion.setText(wordGroups[levelNumber-1][mnumber-1]);
                mtextViewNumber.setText("題數:"+mnumber+"/"+wordGroups[levelNumber-1].length);

                meditTextInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                        // 文本變化前的處理
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        // 文本變化時的處理
                        String userInput = charSequence.toString();
                        Log.d("使用者輸入", userInput);
                        // 在這裡你可以執行檢測邏輯
                        checkInput(userInput);
                        Log.d("bool", String.valueOf(checkInputCorrect(userInput)));
                        if(checkInputCorrect(userInput)) {
                            if(mnumber==wordGroups[levelNumber-1].length){
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                intent.putExtra("Number", mnumber);
                                intent.putExtra("Time", seconds);
                                intent.putExtra("questionData",wordGroups[levelNumber-1]);
                                intent.putExtra("wordGroups",wordGroups[levelNumber-1]);
                                startActivity(intent);
                                return;
                            }
                            mnumber+=1;
                            mtextViewNumber.setText("題數:"+mnumber+"/"+wordGroups[levelNumber-1].length);
                            mtextViewQuestion.setText(wordGroups[levelNumber-1][mnumber-1]);
                            meditTextInput.setText("");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        // 文本變化後的處理
                    }
                });
            } else if (source.equals("MainActivity4")) {
                // 這個 Intent 是從 AnotherActivity 啟動的
                mtextViewLevelName.setText("限時模式");

                //倒數計時
                seconds = 30;
                startCountdown();
                // 在這裡對單字數組進行打亂
                shuffleWords();

                mtextViewQuestion.setText(words[mnumber-1]);
                mtextViewNumber.setText("題數:"+mnumber);

                meditTextInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                        // 文本變化前的處理
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        // 文本變化時的處理
                        String userInput = charSequence.toString();
                        Log.d("使用者輸入", userInput);
                        // 在這裡你可以執行檢測邏輯
                        checkInputTime(userInput);
                        if(checkInputCorrectTime(userInput)) {
                            Log.d("seconds", String.valueOf(seconds));
                            mnumber+=1;
                            mtextViewNumber.setText("題數:"+mnumber);
                            mtextViewQuestion.setText(words[mnumber-1]);
                            meditTextInput.setText("");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        // 文本變化後的處理
                    }
                });


            }
        }
    }
//音樂
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(mediaPlayer!=null){
                mediaPlayer.stop();
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.stop();
        mediaPlayer.release();
    }
//靜音
    private void toggleMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }
    }
    // 檢查用戶輸入是否正確
    private void checkInput(String userInput) {
        int range = userInput.length();
        int qusetionlength = wordGroups[levelNumber-1][mnumber-1].length();
        if(range>qusetionlength){
            meditTextInput.setText("");

            return;
        }
        Log.d("range", String.valueOf(range));
        if(range!=0) {
            String userInputchar = String.valueOf(userInput.charAt(range-1));
            Log.d("range", String.valueOf(userInputchar.equalsIgnoreCase(wordGroups[levelNumber-1][mnumber-1].substring(range - 1, range))));
            if (userInputchar.equalsIgnoreCase(wordGroups[levelNumber-1][mnumber-1].substring(range - 1, range))) {
                // 用戶輸入的字元與目標單字的第一個字元匹配
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(wordGroups[levelNumber-1][mnumber-1]);
                ForegroundColorSpan colorSpanCorrect = new ForegroundColorSpan(Color.GREEN);
                spannableStringBuilder.setSpan(colorSpanCorrect, 0, range, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mtextViewQuestion.setText(spannableStringBuilder);
                Log.d("work", String.valueOf(userInput.equalsIgnoreCase(wordGroups[levelNumber-1][mnumber-1].substring(range - 1, range))));
            }
            else{
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(wordGroups[levelNumber-1][mnumber-1]);
                ForegroundColorSpan colorSpanCorrect = new ForegroundColorSpan(Color.GREEN);
                ForegroundColorSpan colorSpanWrong = new ForegroundColorSpan(Color.RED);
                spannableStringBuilder.setSpan(colorSpanCorrect, 0, range-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.setSpan(colorSpanWrong, range-1, range, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mtextViewQuestion.setText(spannableStringBuilder);
                meditTextInput.setText("");
                Log.d("work", String.valueOf(userInput.equalsIgnoreCase(wordGroups[levelNumber-1][mnumber-1].substring(range - 1, range))));
            }
        }
    }

    private boolean checkInputCorrect(String userInput) {
        if(userInput.equalsIgnoreCase(wordGroups[levelNumber-1][mnumber-1])){
            return true;
        }
        return false;
    }

    private void checkInputTime(String userInput) {
        int range = userInput.length();
        int qusetionlength = words[mnumber-1].length();
        if(range>qusetionlength){
            meditTextInput.setText("");

            return;
        }
        Log.d("range", String.valueOf(range));
        if(range!=0) {
            String userInputchar = String.valueOf(userInput.charAt(range-1));
            Log.d("range", String.valueOf(userInputchar.equalsIgnoreCase(words[mnumber-1].substring(range - 1, range))));
            if (userInputchar.equalsIgnoreCase(words[mnumber-1].substring(range - 1, range))) {
                // 用戶輸入的字元與目標單字的第一個字元匹配
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(words[mnumber-1]);
                ForegroundColorSpan colorSpanCorrect = new ForegroundColorSpan(Color.GREEN);
                spannableStringBuilder.setSpan(colorSpanCorrect, 0, range, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mtextViewQuestion.setText(spannableStringBuilder);
                Log.d("work", String.valueOf(userInput.equalsIgnoreCase(words[mnumber-1].substring(range - 1, range))));
            }
            else{
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(words[mnumber-1]);
                ForegroundColorSpan colorSpanCorrect = new ForegroundColorSpan(Color.GREEN);
                ForegroundColorSpan colorSpanWrong = new ForegroundColorSpan(Color.RED);
                spannableStringBuilder.setSpan(colorSpanCorrect, 0, range-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.setSpan(colorSpanWrong, range-1, range, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mtextViewQuestion.setText(spannableStringBuilder);
                meditTextInput.setText("");
                Log.d("work", String.valueOf(userInput.equalsIgnoreCase(words[mnumber-1].substring(range - 1, range))));
            }
        }
    }

    private boolean checkInputCorrectTime(String userInput) {
        if(userInput.equalsIgnoreCase(words[mnumber-1])){
            return true;
        }
        return false;
    }
    // 打亂單字數組的方法
    private void shuffleWords() {
        List<String> wordList = Arrays.asList(words);
        Collections.shuffle(wordList);
        wordList.toArray(words);
    }

    // 在構造函數或其他地方調用這個方法，可以將所有的子數組都打亂
    private void shuffleAllWordGroups() {
        for (String[] wordGroup : wordGroups) {
            shuffleArray(wordGroup);
        }
    }

    // 打亂一個單維數組的方法
    private void shuffleArray(String[] array) {
        List<String> list = new ArrayList<>(Arrays.asList(array));
        Collections.shuffle(list);
        list.toArray(array);
    }

    private void startTimer() {
        // 使用 Handler 定期執行一個 Runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                updateTimerText();

                // 每隔一秒重複執行
                handler.postDelayed(this, 1000);
            }
        };

        // 第一次執行
        handler.post(runnable);
    }

    private void startCountdown() {
        // 使用 Handler 定期執行一個 Runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                // 如果還有剩餘的秒數
                if (seconds > 0) {
                    seconds--;
                    updateTimerText();

                    // 每隔一秒重複執行
                    handler.postDelayed(this, 1000);
                } else {
                    // 如果倒數結束，可以在這裡處理相應的事件
                    // 例如顯示提示、觸發事件等
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("Number", mnumber);
                    intent.putExtra("Time", seconds);
                    intent.putExtra("wordGroups", wordGroups[levelNumber-1]);
                    startActivity(intent);

                    finish();

                    /*
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    return;

                     */
                }
            }
        };

        // 第一次執行
        handler.post(runnable);
    }



    private void updateTimerText() {
        // 將秒數轉換為分鐘:秒的格式
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        String time = String.format("%02d:%02d", minutes, remainingSeconds);

        // 更新 TextView 顯示時間
        mtextViewTimer.setText(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 移除計時器相關的 Runnable
        handler.removeCallbacks(runnable);
    }

}