package com.oluwafemi.payoneer.ui.factory.model;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oluwafemi.payoneer.R;
import com.oluwafemi.payoneer.databinding.FieldImageLabelBinding;
import com.oluwafemi.payoneer.ui.factory.interfaces.FactoryEventListener;

public class ImageLabelField extends UIField {

    private final String label;
    private final String imageUrl;

    public ImageLabelField(String label, String imageUrl) {
        super(R.layout.field_image_label);
        this.label = label;
        this.imageUrl = imageUrl;
    }

    @Override
    public void bind(View itemView, FactoryEventListener factoryEventListener) {
        FieldImageLabelBinding binding = FieldImageLabelBinding.bind(itemView);
        binding.textView.setText(label);
        loadImage(binding.imageView, imageUrl);
        binding.getRoot().setOnClickListener(view -> {
            if (factoryEventListener != null) {
                factoryEventListener.onItemClick(ImageLabelField.this, null);
            }
        });
    }

    private void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .into(imageView);

    }

    @Override
    public Boolean hasValidData() {
        return true;
    }
}
