package com.ansersion.hubing.huailin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansersion.hubing.huailin.MainActivity;
import com.ansersion.hubing.huailin.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hubing on 2018/3/5.
 */

public class TitleBarFragment extends BaseFragment implements BaseFragment.TitleBarChangeHandlerInterface {
    @Bind(R.id.id_title_bar_back_image_view)
    ImageView backImageView;

    @Bind(R.id.id_title_text_view)
    TextView titleTextView;

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title_bar, container, false);

        ButterKnife.bind(this, view);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("Input keyevent" + KeyEvent.KEYCODE_BACK);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                */
                if(BaseFragment.getMyFragmentManager().getBackStackEntryCount() == 1) {
                    // hideBackIcon();
                    MainActivity main_activity = (MainActivity)getActivity();
                    main_activity.mainPanelRestore();
                    // main_activity.showSelectorBar();
                    // main_activity.showTitleBar();
                }
                getMyFragmentManager().popBackStack();

            }
        });

        return view;
    }

    @Override
    public void startFragment(boolean push_back_stack) {
        // FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = getMyFragmentManager().beginTransaction();
        transaction.replace(R.id.id_fragment_title_bar, this);
        if(push_back_stack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void showBackIcon() {
        if(BaseFragment.getMyFragmentManager().getBackStackEntryCount() > 0) {
            backImageView.setVisibility(View.VISIBLE);
        }
    }

    public void hideBackIcon() {
        backImageView.setVisibility(View.GONE);
    }

    public void changeTitle(String title) {
        titleTextView.setText(title);
    }
}
