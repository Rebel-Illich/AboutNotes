package project.ty.aboutnotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;

public class NodeViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private static NodeViewModelFactory instance;

    public static void createFactory(Application application) {
        instance = new NodeViewModelFactory();
        instance.application = application;
    }

    public static NodeViewModelFactory getInstance() {
        return instance;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (!modelClass.equals(NoteViewModel.class)) {
            throw new IllegalArgumentException();
        }
        try {
            Constructor<?> constructor = modelClass.getConstructor(Application.class);
            return (T) constructor.newInstance(application);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
