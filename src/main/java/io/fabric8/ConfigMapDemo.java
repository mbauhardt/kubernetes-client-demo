package io.fabric8;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigMapDemo {
	private static final Logger logger = Logger.getLogger(DeploymentDemo.class
			.getName());

	public static void main(String args[]) {
		String namespace = "default";
		try {
			final KubernetesClient client = new DefaultKubernetesClient();
			ConfigMap configMap1 = new ConfigMapBuilder().withNewMetadata()
					.withName("configmap1").endMetadata().addToData("one", "1")
					.addToData("two", "2").addToData("three", "3").build();

			logger.log(Level.INFO, "Creating config map");
			client.configMaps().inNamespace(namespace).create(configMap1);
			logger.log(Level.INFO, "OK .. ConfigMap successfully created");

			/*
			 * Let's edit a configMap
			 */
			client.configMaps().inNamespace(namespace).withName("configmap1")
					.edit().addToData("three", "3").addToData("four", "4")
					.done();
			
			logger.log(Level.INFO, "Closing client");
			client.close();
		} catch (KubernetesClientException aException) {
			logger.log(Level.SEVERE,
					"Some problem occured related to K8 client");
			aException.printStackTrace();
		}
	}
}
