package org.sagebionetworks.research.psorcast.validation.inject;

import android.app.Application;

import org.sagebionetworks.bridge.android.di.BridgeApplicationScope;
import org.sagebionetworks.research.data.inject.DataModule;
import org.sagebionetworks.research.domain.inject.TaskModule;
import org.sagebionetworks.research.mobile_ui.inject.PerformTaskModule;
import org.sagebionetworks.research.modules.psorcast.inject.PsorcastStepModule;
import org.sagebionetworks.research.psorcast.validation.PsorcastValidationApplication;
import org.sagebionetworks.research.sageresearch_app_sdk.inject.SageResearchAppSDKModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@BridgeApplicationScope
@Component(modules = {PerformTaskModule.class, SageResearchAppSDKModule.class, TaskModule.class,
        PsorcastValidationAppModule.class, AndroidSupportInjectionModule.class, PsorcastStepModule.class,
        DataModule.class}, dependencies = {PsorcastValidationUserScopeComponent.class})
public interface PsorcastValidationAppComponent extends AndroidInjector<PsorcastValidationApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder userScopeComponent(PsorcastValidationUserScopeComponent userScopeComponent);

        PsorcastValidationAppComponent build();
    }
}
