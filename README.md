#### Backlog:
- ✅ Setup mysql
- ✅ Setup H2 for testing
- ✅ Setup Dbeaver
- ✅ setup Influx DB
- ✅ Ability to store the readings
- ✅ Integrate backstage with support of tech docs, k8s
- 🕐 Integration test with Influx DB test container
- 🕐 Cron job to call store readings every 50 ms
- 🕐 Store all secrets in hashicorp vault
- 🕐 Show the electricity usage on the UI (React app)
- 🕐 OAuth for authentication and authorization
- 🕐 Update the electricity usage every 100 ms using SSE
- 🕐 Run frontend and backend inside k8s
- 🕐 Deploy on EKS/GKE
- 🕐 Provision to generate bill
- 🕐 Provision to pay bill

#### Current setup:
- Docker desktop is not installed on the local machine. Hence, cannot run mysql container
- Started running docker container inside minikube. Refer to `/deploy/k8s/mysql-pod.yaml`
- Setup Dbeaver to view the database on the machine.
- Problem is how can Dbeaver connects to the database which is running inside minikube node? Used k8s port forwarding for this `kubectl port-forward db 3306:3306`
- Some public key error. Solved it by selecting `Use SSL` in the connection
- Create table using `/scripts/account-ddl.sql`

#### Application usage:
##### Registration:
Use command below to register to new meter with some plan.
``` 
curl 'http://localhost:8080/register' \
-H 'accept: application/json' \
-H 'content-type: application/json' \
--data-raw '{"smartMeterId":"1","pricePlan": "plan123"}' \
--compressed
```

##### Steps to run backstage locally:
Below are the steps to start the backstage application
1. Run `yarn install` inside folder `energy-solution/backstage/energy-solution/packages/app`
2. Run `yarn install` inside folder `energy-solution/backstage/energy-solution/packages/backend`
3. Run `kubectl proxy --port 8080`. This is required as k8s is throwing 403 when connecting to cluster using service account. This needs to be fixed properly.
4. Run `AUTH_GITHUB_CLIENT_ID={} AUTH_GITHUB_CLIENT_SECRET={} yarn dev`. Set client id and secret prior running this command.