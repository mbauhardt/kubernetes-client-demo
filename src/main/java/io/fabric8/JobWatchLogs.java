package io.fabric8;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class JobWatchLogs {
  public static void main(String[] args) {
    try (KubernetesClient client = new DefaultKubernetesClient()) {
      client.batch().v1()
        .jobs()
        .inNamespace("default")
        .withName("pi")
        .watchLog(System.out);

      Thread.sleep(15 * 1000L);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}
