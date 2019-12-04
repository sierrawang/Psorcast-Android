package org.sagebionetworks.research.psorcast.validation;

import android.app.Activity;
import android.app.Service;

import androidx.fragment.app.Fragment;

import org.sagebionetworks.bridge.android.di.BridgeStudyComponent;
import org.sagebionetworks.research.psorcast.validation.inject.DaggerPsorcastValidationAppComponent;
import org.sagebionetworks.research.psorcast.validation.inject.DaggerPsorcastValidationUserScopeComponent;
import org.sagebionetworks.research.psorcast.validation.inject.PsorcastValidationUserScopeComponent;
import org.sagebionetworks.research.sageresearch.BridgeSageResearchApp;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.DaggerApplication;
import dagger.android.support.HasSupportFragmentInjector;

public class PsorcastValidationApplication extends BridgeSageResearchApp implements HasSupportFragmentInjector,
        HasActivityInjector, HasServiceInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingSupportFragmentInjector;

    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerPsorcastValidationAppComponent
                .builder()
                .application(this)
                .userScopeComponent((PsorcastValidationUserScopeComponent) getOrInitBridgeManagerProvider())
                .build();
    }

    @Override
    protected PsorcastValidationUserScopeComponent initBridgeManagerScopedComponent(BridgeStudyComponent bridgeStudyComponent) {
        PsorcastValidationUserScopeComponent bridgeManagerProvider = DaggerPsorcastValidationUserScopeComponent.builder()
                .applicationContext(this.getApplicationContext())
                .bridgeStudyComponent(bridgeStudyComponent)
                .build();
        return bridgeManagerProvider;
    }

}
