using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net.Http;
using System.Runtime.Remoting.Messaging;
using static System.Collections.Specialized.BitVector32;

namespace POSTer
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static HttpClient client = new HttpClient();

        public MainWindow()
        {
            InitializeComponent();
        }

        private async void SendPostRequestButton_Click(object sender, RoutedEventArgs e)
        {
            using (HttpClient httpClient = new HttpClient())
            {
                var values = new Dictionary<string, string>
                {
                    { "id", "39051" }
                };

                var content = new FormUrlEncodedContent(values);

                try
                {
                    string s = "";
                    var response = await client.PostAsync(TB_1.Text, content);
                    var responseString = await response.Content.ReadAsStringAsync();
                    try {
                        foreach (var header in response.Headers)
                        {
                            s+=($"{header.Key}={header.Value.First()}"+"\n");
                        }
                        foreach (var header in response.Content.Headers)
                        {
                            s += ($"{header.Key}={header.Value.First()}" + "\n");
                        }
                        TBl_1.Text = s;
                    }
                    catch
                    {
                        TBl_1.Text = responseString;
                    }
                    
                    
                }
                catch
                {
                    TBl_1.Text = "Ошибка подключения!";
                }
                
            }
        }
    }
}
