package com.akameko.tarkovmarketmaps.tabs.market;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akameko.tarkovmarketmaps.R;
import com.akameko.tarkovmarketmaps.databinding.FragmentMarketBinding;
import com.akameko.tarkovmarketmaps.webs.MyWebViewClient;

public class MarketFragment extends Fragment {

    FragmentMarketBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        final OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                if (binding.webViewMarket.canGoBack()){
                    binding.webViewMarket.goBack();
                } else {
                   setEnabled(false);
                    requireActivity().onBackPressed();

                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentMarketBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        binding.webViewMarket.setWebViewClient(new MyWebViewClient("tarkov-market.com", getContext()));
        binding.webViewMarket.getSettings().setJavaScriptEnabled(true);

        //binding.webViewMarket.getSettings().setTextZoom(150);
        //binding.webViewMarket.setInitialScale(150);
        binding.webViewMarket.loadUrl("https://tarkov-market.com/");
        binding.webViewMarket.scrollTo(0, 100);
        //binding.webViewMarket.scrollBy(50,50);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.web_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update:
                binding.webViewMarket.reload();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
