package com.oluwafemi.payoneer.ui.factory.model;

import android.view.View;

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
        binding.getRoot().setOnClickListener( view ->
                factoryEventListener.onItemClick(ImageLabelField.this, null)
        );
    }

    @Override
    public Boolean hasValidData() {
        return true;
    }
}
