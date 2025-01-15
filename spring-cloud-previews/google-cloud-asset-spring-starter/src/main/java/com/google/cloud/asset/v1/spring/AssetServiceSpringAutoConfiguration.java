/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.asset.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.asset.v1.AssetServiceClient;
import com.google.cloud.asset.v1.AssetServiceSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link AssetServiceClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(AssetServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.asset.v1.asset-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(AssetServiceSpringProperties.class)
public class AssetServiceSpringAutoConfiguration {
  private final AssetServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(AssetServiceSpringAutoConfiguration.class);

  protected AssetServiceSpringAutoConfiguration(
      AssetServiceSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from AssetService-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean, corresponding to the client library's
   * default transport channel provider. If the library supports both GRPC and REST transport, and
   * the useRest property is configured, the HTTP/JSON transport provider will be used instead of
   * GRPC.
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultAssetServiceTransportChannelProvider")
  public TransportChannelProvider defaultAssetServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return AssetServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return AssetServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a AssetServiceSettings bean configured to use a DefaultCredentialsProvider and the
   * client library's default transport channel provider
   * (defaultAssetServiceTransportChannelProvider()). It also configures the quota project ID and
   * executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in AssetServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link AssetServiceSettings} bean configured with {@link TransportChannelProvider}
   *     bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public AssetServiceSettings assetServiceSettings(
      @Qualifier("defaultAssetServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    AssetServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = AssetServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = AssetServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setEndpoint(AssetServiceSettings.getDefaultEndpoint())
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          AssetServiceSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings listAssetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAssetsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listAssetsSettings().setRetrySettings(listAssetsRetrySettings);

      RetrySettings batchGetAssetsHistoryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.batchGetAssetsHistorySettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .batchGetAssetsHistorySettings()
          .setRetrySettings(batchGetAssetsHistoryRetrySettings);

      RetrySettings createFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createFeedSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createFeedSettings().setRetrySettings(createFeedRetrySettings);

      RetrySettings getFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getFeedSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getFeedSettings().setRetrySettings(getFeedRetrySettings);

      RetrySettings listFeedsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listFeedsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listFeedsSettings().setRetrySettings(listFeedsRetrySettings);

      RetrySettings updateFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateFeedSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateFeedSettings().setRetrySettings(updateFeedRetrySettings);

      RetrySettings deleteFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteFeedSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteFeedSettings().setRetrySettings(deleteFeedRetrySettings);

      RetrySettings searchAllResourcesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchAllResourcesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .searchAllResourcesSettings()
          .setRetrySettings(searchAllResourcesRetrySettings);

      RetrySettings searchAllIamPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchAllIamPoliciesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .searchAllIamPoliciesSettings()
          .setRetrySettings(searchAllIamPoliciesRetrySettings);

      RetrySettings analyzeIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .analyzeIamPolicySettings()
          .setRetrySettings(analyzeIamPolicyRetrySettings);

      RetrySettings analyzeMoveRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeMoveSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.analyzeMoveSettings().setRetrySettings(analyzeMoveRetrySettings);

      RetrySettings queryAssetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.queryAssetsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.queryAssetsSettings().setRetrySettings(queryAssetsRetrySettings);

      RetrySettings createSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createSavedQuerySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .createSavedQuerySettings()
          .setRetrySettings(createSavedQueryRetrySettings);

      RetrySettings getSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getSavedQuerySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getSavedQuerySettings().setRetrySettings(getSavedQueryRetrySettings);

      RetrySettings listSavedQueriesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listSavedQueriesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listSavedQueriesSettings()
          .setRetrySettings(listSavedQueriesRetrySettings);

      RetrySettings updateSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateSavedQuerySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .updateSavedQuerySettings()
          .setRetrySettings(updateSavedQueryRetrySettings);

      RetrySettings deleteSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteSavedQuerySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .deleteSavedQuerySettings()
          .setRetrySettings(deleteSavedQueryRetrySettings);

      RetrySettings batchGetEffectiveIamPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.batchGetEffectiveIamPoliciesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .batchGetEffectiveIamPoliciesSettings()
          .setRetrySettings(batchGetEffectiveIamPoliciesRetrySettings);

      RetrySettings analyzeOrgPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeOrgPoliciesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .analyzeOrgPoliciesSettings()
          .setRetrySettings(analyzeOrgPoliciesRetrySettings);

      RetrySettings analyzeOrgPolicyGovernedContainersRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeOrgPolicyGovernedContainersSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .analyzeOrgPolicyGovernedContainersSettings()
          .setRetrySettings(analyzeOrgPolicyGovernedContainersRetrySettings);

      RetrySettings analyzeOrgPolicyGovernedAssetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeOrgPolicyGovernedAssetsSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .analyzeOrgPolicyGovernedAssetsSettings()
          .setRetrySettings(analyzeOrgPolicyGovernedAssetsRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry listAssetsRetry = clientProperties.getListAssetsRetry();
    if (listAssetsRetry != null) {
      RetrySettings listAssetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAssetsSettings().getRetrySettings(), listAssetsRetry);
      clientSettingsBuilder.listAssetsSettings().setRetrySettings(listAssetsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listAssets from properties.");
      }
    }
    Retry batchGetAssetsHistoryRetry = clientProperties.getBatchGetAssetsHistoryRetry();
    if (batchGetAssetsHistoryRetry != null) {
      RetrySettings batchGetAssetsHistoryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.batchGetAssetsHistorySettings().getRetrySettings(),
              batchGetAssetsHistoryRetry);
      clientSettingsBuilder
          .batchGetAssetsHistorySettings()
          .setRetrySettings(batchGetAssetsHistoryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for batchGetAssetsHistory from properties.");
      }
    }
    Retry createFeedRetry = clientProperties.getCreateFeedRetry();
    if (createFeedRetry != null) {
      RetrySettings createFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createFeedSettings().getRetrySettings(), createFeedRetry);
      clientSettingsBuilder.createFeedSettings().setRetrySettings(createFeedRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createFeed from properties.");
      }
    }
    Retry getFeedRetry = clientProperties.getGetFeedRetry();
    if (getFeedRetry != null) {
      RetrySettings getFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getFeedSettings().getRetrySettings(), getFeedRetry);
      clientSettingsBuilder.getFeedSettings().setRetrySettings(getFeedRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getFeed from properties.");
      }
    }
    Retry listFeedsRetry = clientProperties.getListFeedsRetry();
    if (listFeedsRetry != null) {
      RetrySettings listFeedsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listFeedsSettings().getRetrySettings(), listFeedsRetry);
      clientSettingsBuilder.listFeedsSettings().setRetrySettings(listFeedsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listFeeds from properties.");
      }
    }
    Retry updateFeedRetry = clientProperties.getUpdateFeedRetry();
    if (updateFeedRetry != null) {
      RetrySettings updateFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateFeedSettings().getRetrySettings(), updateFeedRetry);
      clientSettingsBuilder.updateFeedSettings().setRetrySettings(updateFeedRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateFeed from properties.");
      }
    }
    Retry deleteFeedRetry = clientProperties.getDeleteFeedRetry();
    if (deleteFeedRetry != null) {
      RetrySettings deleteFeedRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteFeedSettings().getRetrySettings(), deleteFeedRetry);
      clientSettingsBuilder.deleteFeedSettings().setRetrySettings(deleteFeedRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteFeed from properties.");
      }
    }
    Retry searchAllResourcesRetry = clientProperties.getSearchAllResourcesRetry();
    if (searchAllResourcesRetry != null) {
      RetrySettings searchAllResourcesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchAllResourcesSettings().getRetrySettings(),
              searchAllResourcesRetry);
      clientSettingsBuilder
          .searchAllResourcesSettings()
          .setRetrySettings(searchAllResourcesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for searchAllResources from properties.");
      }
    }
    Retry searchAllIamPoliciesRetry = clientProperties.getSearchAllIamPoliciesRetry();
    if (searchAllIamPoliciesRetry != null) {
      RetrySettings searchAllIamPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchAllIamPoliciesSettings().getRetrySettings(),
              searchAllIamPoliciesRetry);
      clientSettingsBuilder
          .searchAllIamPoliciesSettings()
          .setRetrySettings(searchAllIamPoliciesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for searchAllIamPolicies from properties.");
      }
    }
    Retry analyzeIamPolicyRetry = clientProperties.getAnalyzeIamPolicyRetry();
    if (analyzeIamPolicyRetry != null) {
      RetrySettings analyzeIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeIamPolicySettings().getRetrySettings(),
              analyzeIamPolicyRetry);
      clientSettingsBuilder
          .analyzeIamPolicySettings()
          .setRetrySettings(analyzeIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for analyzeIamPolicy from properties.");
      }
    }
    Retry analyzeMoveRetry = clientProperties.getAnalyzeMoveRetry();
    if (analyzeMoveRetry != null) {
      RetrySettings analyzeMoveRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeMoveSettings().getRetrySettings(), analyzeMoveRetry);
      clientSettingsBuilder.analyzeMoveSettings().setRetrySettings(analyzeMoveRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for analyzeMove from properties.");
      }
    }
    Retry queryAssetsRetry = clientProperties.getQueryAssetsRetry();
    if (queryAssetsRetry != null) {
      RetrySettings queryAssetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.queryAssetsSettings().getRetrySettings(), queryAssetsRetry);
      clientSettingsBuilder.queryAssetsSettings().setRetrySettings(queryAssetsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for queryAssets from properties.");
      }
    }
    Retry createSavedQueryRetry = clientProperties.getCreateSavedQueryRetry();
    if (createSavedQueryRetry != null) {
      RetrySettings createSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createSavedQuerySettings().getRetrySettings(),
              createSavedQueryRetry);
      clientSettingsBuilder
          .createSavedQuerySettings()
          .setRetrySettings(createSavedQueryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createSavedQuery from properties.");
      }
    }
    Retry getSavedQueryRetry = clientProperties.getGetSavedQueryRetry();
    if (getSavedQueryRetry != null) {
      RetrySettings getSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getSavedQuerySettings().getRetrySettings(), getSavedQueryRetry);
      clientSettingsBuilder.getSavedQuerySettings().setRetrySettings(getSavedQueryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getSavedQuery from properties.");
      }
    }
    Retry listSavedQueriesRetry = clientProperties.getListSavedQueriesRetry();
    if (listSavedQueriesRetry != null) {
      RetrySettings listSavedQueriesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listSavedQueriesSettings().getRetrySettings(),
              listSavedQueriesRetry);
      clientSettingsBuilder
          .listSavedQueriesSettings()
          .setRetrySettings(listSavedQueriesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listSavedQueries from properties.");
      }
    }
    Retry updateSavedQueryRetry = clientProperties.getUpdateSavedQueryRetry();
    if (updateSavedQueryRetry != null) {
      RetrySettings updateSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateSavedQuerySettings().getRetrySettings(),
              updateSavedQueryRetry);
      clientSettingsBuilder
          .updateSavedQuerySettings()
          .setRetrySettings(updateSavedQueryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateSavedQuery from properties.");
      }
    }
    Retry deleteSavedQueryRetry = clientProperties.getDeleteSavedQueryRetry();
    if (deleteSavedQueryRetry != null) {
      RetrySettings deleteSavedQueryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteSavedQuerySettings().getRetrySettings(),
              deleteSavedQueryRetry);
      clientSettingsBuilder
          .deleteSavedQuerySettings()
          .setRetrySettings(deleteSavedQueryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for deleteSavedQuery from properties.");
      }
    }
    Retry batchGetEffectiveIamPoliciesRetry =
        clientProperties.getBatchGetEffectiveIamPoliciesRetry();
    if (batchGetEffectiveIamPoliciesRetry != null) {
      RetrySettings batchGetEffectiveIamPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.batchGetEffectiveIamPoliciesSettings().getRetrySettings(),
              batchGetEffectiveIamPoliciesRetry);
      clientSettingsBuilder
          .batchGetEffectiveIamPoliciesSettings()
          .setRetrySettings(batchGetEffectiveIamPoliciesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for batchGetEffectiveIamPolicies from properties.");
      }
    }
    Retry analyzeOrgPoliciesRetry = clientProperties.getAnalyzeOrgPoliciesRetry();
    if (analyzeOrgPoliciesRetry != null) {
      RetrySettings analyzeOrgPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeOrgPoliciesSettings().getRetrySettings(),
              analyzeOrgPoliciesRetry);
      clientSettingsBuilder
          .analyzeOrgPoliciesSettings()
          .setRetrySettings(analyzeOrgPoliciesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for analyzeOrgPolicies from properties.");
      }
    }
    Retry analyzeOrgPolicyGovernedContainersRetry =
        clientProperties.getAnalyzeOrgPolicyGovernedContainersRetry();
    if (analyzeOrgPolicyGovernedContainersRetry != null) {
      RetrySettings analyzeOrgPolicyGovernedContainersRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeOrgPolicyGovernedContainersSettings().getRetrySettings(),
              analyzeOrgPolicyGovernedContainersRetry);
      clientSettingsBuilder
          .analyzeOrgPolicyGovernedContainersSettings()
          .setRetrySettings(analyzeOrgPolicyGovernedContainersRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for analyzeOrgPolicyGovernedContainers from properties.");
      }
    }
    Retry analyzeOrgPolicyGovernedAssetsRetry =
        clientProperties.getAnalyzeOrgPolicyGovernedAssetsRetry();
    if (analyzeOrgPolicyGovernedAssetsRetry != null) {
      RetrySettings analyzeOrgPolicyGovernedAssetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.analyzeOrgPolicyGovernedAssetsSettings().getRetrySettings(),
              analyzeOrgPolicyGovernedAssetsRetry);
      clientSettingsBuilder
          .analyzeOrgPolicyGovernedAssetsSettings()
          .setRetrySettings(analyzeOrgPolicyGovernedAssetsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for analyzeOrgPolicyGovernedAssets from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a AssetServiceClient bean configured with AssetServiceSettings.
   *
   * @param assetServiceSettings settings to configure an instance of client bean.
   * @return a {@link AssetServiceClient} bean configured with {@link AssetServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public AssetServiceClient assetServiceClient(AssetServiceSettings assetServiceSettings)
      throws IOException {
    return AssetServiceClient.create(assetServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-asset-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
