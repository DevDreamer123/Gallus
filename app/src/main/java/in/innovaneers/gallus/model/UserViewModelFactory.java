package in.innovaneers.gallus.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import in.innovaneers.gallus.model.Repository;
import kotlin.reflect.KClass;

/*public class UserViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public UserViewModelFactory(Repository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull KClass<T> modelClass, @NonNull CreationExtras extras) {
        if (modelClass.equals(UserViewModel.class)){
            return (T) new UserViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown viewmodel class");
    }


}*/
