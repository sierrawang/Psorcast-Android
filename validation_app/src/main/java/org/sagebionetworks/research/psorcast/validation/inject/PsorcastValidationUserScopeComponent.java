package org.sagebionetworks.research.psorcast.validation.inject;

import android.content.Context;

import org.sagebionetworks.bridge.android.di.BridgeStudyComponent;
import org.sagebionetworks.bridge.android.di.BridgeStudyParticipantScope;
import org.sagebionetworks.bridge.android.manager.BridgeManagerProvider;

import dagger.BindsInstance;
import dagger.Component;

/**
 * A scope tied to bridge study and a particular participant.
 */
@Component(modules = {}, dependencies = BridgeStudyComponent.class)
@BridgeStudyParticipantScope
public interface PsorcastValidationUserScopeComponent extends BridgeManagerProvider {
    @Component.Builder
    interface Builder extends BridgeManagerProvider.Builder {
        @BindsInstance
        @Override
        Builder applicationContext(Context context);

        @Override
        Builder bridgeStudyComponent(BridgeStudyComponent bridgeStudyComponent);

        @Override
        PsorcastValidationUserScopeComponent build();
    }
}
