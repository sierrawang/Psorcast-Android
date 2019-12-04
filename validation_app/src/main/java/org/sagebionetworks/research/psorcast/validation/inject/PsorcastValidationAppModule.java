package org.sagebionetworks.research.psorcast.validation.inject;

import com.google.common.collect.ImmutableList;

import org.sagebionetworks.bridge.android.di.BridgeApplicationScope;
import org.sagebionetworks.research.mobile_ui.inject.ShowStepFragmentModule;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment;
import org.sagebionetworks.research.modules.psorcast.inject.PsorcastShowStepFragmentsModule;
import org.sagebionetworks.research.psorcast.validation.MainActivity;
import org.sagebionetworks.research.psorcast.validation.ui.main.MainFragment;
import org.sagebionetworks.research.psorcast.validation.ui.researcher_sign_in.ResearcherSignInFragment;
import org.sagebionetworks.research.psorcast.validation.ui.task_list.TaskListFragment;
import org.sagebionetworks.research.sageresearch_app_sdk.archive.AbstractResultArchiveFactory;
import org.sagebionetworks.research.sageresearch_app_sdk.archive.AnswerResultArchiveFactory;
import org.sagebionetworks.research.sageresearch_app_sdk.archive.BaseResultArchiveFactory;
import org.sagebionetworks.research.sageresearch_app_sdk.archive.FileResultArchiveFactory;
import org.sagebionetworks.research.sageresearch_app_sdk.archive.TaskResultAnswerMapResultArchiveFactory;
import org.sagebionetworks.research.sageresearch_app_sdk.archive.TaskResultArchiveFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PsorcastValidationAppModule {

    @ContributesAndroidInjector
    abstract MainFragment contributesMainFragmentInjector();

    @ContributesAndroidInjector
    abstract TaskListFragment contributesTaskListFragmentInjector();

    @ContributesAndroidInjector
    abstract ResearcherSignInFragment contributesResearcherSignInFragmentInjector();

    @ContributesAndroidInjector
    abstract PerformTaskActivity contributePerformTaskActivityInjector();

    @ContributesAndroidInjector(modules = {ShowStepFragmentModule.class, PsorcastShowStepFragmentsModule.class})
    abstract PerformTaskFragment contributePerformTaskFragmentInjector();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();

    @Provides
    @BridgeApplicationScope
    static TaskResultArchiveFactory provideTaskResultArchiveFactory() {
        return new TaskResultAnswerMapResultArchiveFactory();
    }

    @Provides
    @BridgeApplicationScope
    static ImmutableList<AbstractResultArchiveFactory.ResultArchiveFactory> provideAbstractResultArchiveFactory(
            FileResultArchiveFactory fileResultArchiveFactory,
            AnswerResultArchiveFactory answerResultArchiveFactory,
            BaseResultArchiveFactory baseResultArchiveFactory) {
        return ImmutableList.of(fileResultArchiveFactory, answerResultArchiveFactory, baseResultArchiveFactory);
    }
}
