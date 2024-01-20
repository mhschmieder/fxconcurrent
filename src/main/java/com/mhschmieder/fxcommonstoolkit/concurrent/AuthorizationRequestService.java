/**
 * MIT License
 *
 * Copyright (c) 2020, 2024 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the FxCommonsToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxCommonsToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxcommonstoolkit
 */
package com.mhschmieder.fxcommonstoolkit.concurrent;

import com.mhschmieder.commonstoolkit.net.AuthorizationServerResponse;
import com.mhschmieder.commonstoolkit.net.ServerRequestProperties;
import com.mhschmieder.commonstoolkit.security.LoginCredentials;
import com.mhschmieder.commonstoolkit.util.ClientProperties;

import javafx.scene.control.Dialog;
import javafx.util.Pair;

/**
 * Implementation class for specifics of authorization server requests.
 */
public final class AuthorizationRequestService extends ServerRequestService< AuthorizationServerResponse > {

    /** Cache the Login Credentials to use for authorizing the request. */
    protected LoginCredentials                 loginCredentials;

    /** Reference to a Login Dialog that instigates the authorization. */
    protected Dialog< Pair< String, String > > loginDialog;

    public AuthorizationRequestService( final ServerRequestProperties pServerRequestProperties,
                                        final ClientProperties pClientProperties ) {
        // Always call the superclass constructor first!
        super( pServerRequestProperties,
               pClientProperties );

        loginCredentials = new LoginCredentials();

        // Not all authorization requests are launched from Login Dialogs.
        loginDialog = null;
    }

    @Override
    protected AuthorizationRequestTask createTask() {
        // Create a new Authorization Request Task.
        final AuthorizationRequestTask authorizationrequestTask =
                                                                new AuthorizationRequestTask( loginCredentials,
                                                                                              serverRequestProperties,
                                                                                              clientProperties );

        return authorizationrequestTask;
    }

    public void requestUserAuthorization( final LoginCredentials pLoginCredentials ) {
        // Disregard empty, null, or invalid Login Credentials.
        if ( !pLoginCredentials.isValid() ) {
            return;
        }

        // Make sure the authorization parameter sources are up to date.
        setLoginCredentials( pLoginCredentials );

        // Restart the Service as this also cancels old tasks and then resets.
        restart();
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }
    
    public void setLoginCredentials( final LoginCredentials pLoginCredentials ) {
        loginCredentials = pLoginCredentials;
    }

    public Dialog< Pair< String, String > > getLoginDialog() {
        return loginDialog;
    }

    public void setLoginDialog( final Dialog< Pair< String, String > > pLoginDialog ) {
        loginDialog = pLoginDialog;
    }
}
