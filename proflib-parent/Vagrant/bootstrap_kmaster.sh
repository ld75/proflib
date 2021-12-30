#!/bin/bash

echo "[TASK 1] Pull required containers"
kubeadm config images pull >/dev/null 2>&1

echo "[TASK 2] Initialize Kubernetes Cluster"
kubeadm init --apiserver-advertise-address=172.16.16.100 --pod-network-cidr=192.168.0.0/16 >> /root/kubeinit.log 2>/dev/null

echo "[TASK 3] Deploy Calico network"
#kubectl --kubeconfig=/etc/kubernetes/admin.conf create -f https://docs.projectcalico.org/v3.18/manifests/calico.yaml >/dev/null 2>&1
echo "INSTALLATION DE CALICO (voirhttps://projectcalico.docs.tigera.io/getting-started/kubernetes/) Execute the following commands to configure kubectl (also returned by kubeadm init)."
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

echo "Install the Tigera Calico operator and custom resource definitions."
kubectl create -f https://docs.projectcalico.org/manifests/tigera-operator.yaml
echo "Install Calico by creating the necessary custom resource. For more information on configuration options available in this manifest"
kubectl create -f https://docs.projectcalico.org/manifests/custom-resources.yaml

echo "maintenant les pods calico doivent tourner."
echo "Remove the taints on the master so that you can schedule pods on it.It should return the following: node/<your-hostname> untainted"
kubectl taint nodes --all node-role.kubernetes.io/master-

echo "Confirm that you now have a node in your cluster with the following command. It should return your cluster description"
kubectl get nodes -o wide

echo "[TASK 4] Generate and save cluster join command to /joincluster.sh"
kubeadm token create --print-join-command > /joincluster.sh 2>/dev/null