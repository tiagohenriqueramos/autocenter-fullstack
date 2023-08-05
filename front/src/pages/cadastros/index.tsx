import { FormEvent, useContext, useState} from 'react'
import Head from 'next/head'
import logoImg from '@/../public/logoauto.png'
import styles from '@/styles/home.module.scss'

import Image from 'next/image'
import { Input } from '@/components/ui/Input'
import { Button } from '@/components/ui/Button'

import Link from 'next/link'
import { AuthContext } from '@/contexts/AuthContext'
import { toast } from 'react-toastify'

import  { canSSRGuest } from '../../utils/canSSRGuest'


export default function Cadastro() {
    const { signUp } = useContext(AuthContext);
  
    const [nome, setNome] = useState('')
    const [email, setEmail] = useState('')
    const [senha, setSenha] = useState('')
  
    const [loading, setLoading] = useState(false);
  
    async function handleSignUp(event: FormEvent){
      event.preventDefault();
  
      if(nome === '' || email === '' || senha === ''){
        toast.error("Preencha todos os campos")
        return;
      }
  
      setLoading(true);
  
      let data = {
        nome,
        email,
        senha
      }
  
      await signUp(data)
  
      setLoading(false);
  
    }



  return (
   
    < >
      <Head>
        <title>Faça seu cadastro agora!</title>

      </Head>
      
    <div className={styles.containerCenter}>
      <Image src={logoImg} alt="Logo Auto Center"/>
      <div className={styles.login}>
        <h1>Cadastre-se agora!</h1>

      <form onSubmit={handleSignUp}>
        <Input
          placeholder="Digite seu nome"
          type='text'
          value={nome}
          onChange={(e)=> setNome(e.target.value)}
        />
         <Input
          placeholder="Digite seu email"
          type='text'
          value={email}
          onChange={(e)=> setEmail(e.target.value)}
        />
         <Input
          placeholder="Digite sua senha"
          type='password'
          value={senha}
          onChange={(e)=> setSenha(e.target.value)}
        />
       

        <Button type="submit"
        loading={loading}>

          Cadastrar
        </Button>
      </form>
      <Link href="/" passHref>
        <div className={styles.text}>
          Já possui uma conta? Faça login!
        </div>
      </Link>
      </div>
    </div>
    </>
  )
}
export const getServerSideProps = canSSRGuest(async(context) => {

  return{
    props: {}
  }
})