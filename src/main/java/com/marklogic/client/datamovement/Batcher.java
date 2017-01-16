/*
 * Copyright 2015-2017 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marklogic.client.datamovement;

/** The base class (shared methods) for {@link QueryBatcher} and {@link WriteBatcher}. */
public interface Batcher {
  /**
   * Sets the name of the job to help with managing multiple concurrent jobs.
   *
   * @param jobName the name you would like to assign to this job
   * @return this instance (for method chaining)
   */
  Batcher withJobName(String jobName);

  /**
   * @return the job name
   */
  String getJobName();

  /**
   * The size of each batch (usually 50-500).  With some experimentation with
   * your custom job, this value can be tuned.  Tuning this value is one of the
   * best ways to achieve optimal throughput.
   *
   * This method cannot be called after the job has started.
   *
   * @param batchSize the batch size -- must be 1 or greater
   * @return this instance (for method chaining)
   */
  Batcher withBatchSize(int batchSize);

  /**
   * @return the batch size
   */
  int getBatchSize();

  /**
   * The number of threads to be used internally by this job to perform
   * concurrent tasks on batches (usually > 10).  With some experimentation with your custom
   * job and client environment, this value can be tuned.  Tuning this value is
   * one of the best ways to achieve optimal throughput or to throttle the
   * server resources used by this job.  Setting this to 1 does not guarantee
   * that batches will be processed sequentially because the calling thread
   * will sometimes also process batches.
   *
   * This method cannot be called after the job has started.
   *
   * @return this instance (for method chaining)
   */
  Batcher withThreadCount(int threadCount);

  /**
   * @return the thread count
   */
  int getThreadCount();

  /**
   * @return the forest configuration in use by this job
   */
  ForestConfiguration getForestConfig();

  /**
   * Updates the ForestConfiguration used by this job to spread the writes or reads.
   * This can be called mid-job in order to accommodate for node failures or other
   * changes without requiring a restart of this job.  Ideally, this ForestConfiguration
   * will come from {@link DataMovementManager#readForestConfig}, perhaps wrapped by
   * something like {@link FilteredForestConfiguration}.
   *
   * @return this instance (for method chaining)
   */
  Batcher withForestConfig(ForestConfiguration forestConfig);

  /**
   * true if the job is terminated (e.g. {@link DataMovementManager#stopJob
   * DataMovementManager.stopJob} was called), false otherwise
   *
   * @return true if the job is terminated (e.g. {@link
   * DataMovementManager#stopJob DataMovementManager.stopJob} was called), false otherwise
   */
  boolean isStopped();

  /**
   * After the job has been started, returns the JobTicket generated when the
   * job was started.
   *
   * @return the JobTicket generated when this job was started
   *
   * @throws IllegalStateException if this job has not yet been started
   */
  JobTicket getJobTicket();
}
