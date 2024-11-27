
# Local Server & MiniKube 도전

## Helm

1. `curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3`
2. `chmod 700 get_helm.sh`
3. `./get_helm.sh`

## WEB으로 접근

### Ingress 설치

1. `minikube enable nginx-ingress`

## HTTPS 적용

1. cert-manager 설치

명령어 : `kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.15.2/cert-manager.yaml`

2. Issuer 생성
```
apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: letsencrypt-prod
spec:
  acme:
    server: https://acme-v02.api.letsencrypt.org/directory
    email: <EMAIL>
    privateKeySecretRef:
      name: letsencrypt-prod
    solvers:
    - http01:
        ingress:
          class: nginx
```

3. Certificates 설정
```
apiVersion: cert-manager.io/v1
kind: Certificate
metadata:
  name: tls-secret
  namespace: default
spec:
  secretName: tls-secret
  issuerRef:
    name: letsencrypt-prod
    kind: ClusterIssuer
  commonName: "*.heesang.pro"
  dnsNames:
  - "*.heesang.pro"
```

