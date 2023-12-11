FROM node:18.18.0 as build

WORKDIR /app


COPY package*.json ./


RUN npm install -g @angular/cli@16.2.5
RUN npm install


COPY . .


RUN ng build --configuration=production

FROM nginx:alpine

COPY --from=build /app/dist/skuska-fe /usr/share/nginx/html


EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]