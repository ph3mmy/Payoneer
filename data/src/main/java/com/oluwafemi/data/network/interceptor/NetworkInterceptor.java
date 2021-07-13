package com.oluwafemi.data.network.interceptor;

import android.content.Context;

import com.oluwafemi.data.R;
import com.oluwafemi.data.network.util.ConnectivityStatus;
import com.oluwafemi.data.network.util.NetworkEvent;
import com.oluwafemi.data.network.util.NetworkState;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor {

    private final Context context;
    private final NetworkEvent networkEvent;

    @Inject
    public NetworkInterceptor(@ApplicationContext Context context, NetworkEvent networkEvent) {
        this.context = context;
        this.networkEvent = networkEvent;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!ConnectivityStatus.isConnected(context)) {
            networkEvent.publish(NetworkState.NO_INTERNET);
            throw new IOException(context.getString(R.string.error_not_connected));
        } else {
            try {
                Response response = chain.proceed(request);
                switch (response.code()) {
                    case 401:
                        networkEvent.publish(NetworkState.UNAUTHORISED);
                        throw new IOException(context.getString(R.string.error_unauthorized));
                    case 404:
                        networkEvent.publish(NetworkState.NOT_FOUND);
                        throw new IOException(context.getString(R.string.error_resource_not_found));
                    case 500:
                        networkEvent.publish(NetworkState.SERVER_ERROR);
                        throw new IOException(context.getString(R.string.error_server_error));
                    case 503:
                        networkEvent.publish(NetworkState.NO_RESPONSE);
                        throw new IOException(context.getString(R.string.error_no_response));
                }
                return response;

            } catch (IOException e) {
                networkEvent.publish(NetworkState.NO_RESPONSE);
                throw new IOException(context.getString(R.string.error_no_response));
            }
        }
    }
}
