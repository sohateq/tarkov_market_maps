package com.akameko.tarkovmarketmaps.tabs.map;

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
import com.akameko.tarkovmarketmaps.databinding.FragmentMapBinding;
import com.akameko.tarkovmarketmaps.webs.MyWebViewClient;

public class MapFragment extends Fragment {

    FragmentMapBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        final OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                if (binding.webViewMap.canGoBack()){
                    binding.webViewMap.goBack();
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

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.webViewMap.setWebViewClient(new MyWebViewClient("tarkovmap.ru", getContext()));
        binding.webViewMap.getSettings().setJavaScriptEnabled(true);

        binding.webViewMap.getSettings().setTextZoom(150);
        binding.webViewMap.setInitialScale(150);
        binding.webViewMap.loadUrl("https://tarkovmap.ru/");



        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
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
                binding.webViewMap.reload();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
