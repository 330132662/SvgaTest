package cn.jfcat.testsvgahalfscreen;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private SVGAImageView svga_view;
    private ImageView test_jp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    SVGAParser parser;
    private BottomSheetDialog eggRewardDialog;
    private View eggSheet, eggRewardSheet;

    public void showAnim(View view) {
//        show();
        openBottom();
    }

    public void hitEgg(View view) {
        show();
    }

    private void openBottom() {
        eggRewardDialog = new BottomSheetDialog(this, R.style.dialog_default_style);//, R.style.dialog_default_style
        eggRewardSheet = View.inflate(this, R.layout.egg_reward_dialog, null);
        eggRewardDialog.setContentView(eggRewardSheet);

        svga_view = eggRewardDialog.findViewById(R.id.svga_view1);
        test_jp = eggRewardDialog.findViewById(R.id.test_jp);
        eggRewardDialog.show();
    }

    private void show() {
        svga_view.setVisibility(View.VISIBLE);
        test_jp.setVisibility(View.GONE);
        //            方案3-svga
        parser = new SVGAParser(this);

            /*try {
                parser.decodeFromURL(new URL("https://github.com/yyued/SVGA-Samples/blob/master/posche.svga?raw=true"), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        SVGADrawable drawable = new SVGADrawable(videoItem);
                        svga_view.setImageDrawable(drawable);
                        svga_view.startAnimation();
                    }
                    @Override
                    public void onError() {
                        DebugLogUtil.getInstance().Error("svga 加载失败");
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/

        parser.decodeFromAssets("jp_test1.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity videoItem) {
//                DebugLogUtil.getInstance().Error("svga 加载---");
                SVGADrawable drawable = new SVGADrawable(videoItem);
                svga_view.setImageDrawable(drawable);
                svga_view.startAnimation();
            }

            @Override
            public void onError() {
//                DebugLogUtil.getInstance().Error("svga 加载失败");
            }
        });
    }
}
