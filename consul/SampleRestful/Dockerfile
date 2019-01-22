FROM microsoft/dotnet:2.2-aspnetcore-runtime AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM microsoft/dotnet:2.2-sdk AS build
WORKDIR /src
COPY ["SampleRestful/SampleRestful.csproj", "SampleRestful/"]
RUN dotnet restore "SampleRestful/SampleRestful.csproj"
COPY . .
WORKDIR "/src/SampleRestful"
RUN dotnet build "SampleRestful.csproj" -c Release -o /app

FROM build AS publish
RUN dotnet publish "SampleRestful.csproj" -c Release -o /app

FROM base AS final
WORKDIR /app
COPY --from=publish /app .
ENTRYPOINT ["dotnet", "SampleRestful.dll"]